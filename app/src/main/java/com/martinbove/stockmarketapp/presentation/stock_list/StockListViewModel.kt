package com.martinbove.stockmarketapp.presentation.stock_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martinbove.stockmarketapp.domain.repository.StockRepository
import com.martinbove.stockmarketapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Martín Bove on 11/08/2023.
 * E-mail: mbove77@gmail.com
 */
@HiltViewModel
class StockListViewModel @Inject constructor(private val stockRepository: StockRepository): ViewModel() {

    var state by mutableStateOf(StockListState())
    private var searchJob: Job? = null

    fun onEvent(events: StockListEvents) {
        when(events) {
            is StockListEvents.Refresh -> {
                getStockList(fetchFromRemote = true)
            }
            is StockListEvents.OnSearchQueryChange -> {
                state = state.copy(searchQuery = events.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getStockList()
                }
            }
        }
    }

    private fun getStockList(
        query: String = state.searchQuery.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            stockRepository.getStockList(fetchFromRemote, query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { stockList ->
                                state = state.copy(companies = stockList)
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}