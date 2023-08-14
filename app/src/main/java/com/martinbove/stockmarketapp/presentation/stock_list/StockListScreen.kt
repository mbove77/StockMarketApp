package com.martinbove.stockmarketapp.presentation.stock_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

/**
 * Created by Martín Bove on 14/08/2023.
 * E-mail: mbove77@gmail.com
 */

@Composable
@Destination(start = true)
fun StockListScreen(
    navigator: DestinationsNavigator,
    viewModel: StockListViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.state.isRefreshing)

    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(StockListEvents.OnSearchQueryChange(it)) },
            placeholder = {Text(text = "Search...")},
            maxLines = 1,
            singleLine = true
        )

        SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.onEvent(StockListEvents.Refresh) }) {
            
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.companies.size) { i ->
                    val stock = state.companies[i]

                    StockItem(
                        stock = stock,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //TODO: Navigate to detail }
                            }
                            .padding(16.dp)
                    )

                    if (i < state.companies.size) {
                        Divider(modifier = Modifier.padding(horizontal = 16.dp))
                    }
                }
            }
        }
    }
}