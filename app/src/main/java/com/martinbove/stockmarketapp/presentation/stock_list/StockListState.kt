package com.martinbove.stockmarketapp.presentation.stock_list

import com.martinbove.stockmarketapp.domain.model.Stock

/**
 * Created by Martín Bove on 11/08/2023.
 * E-mail: mbove77@gmail.com
 */

data class StockListState(
    val companies: List<Stock> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)