package com.martinbove.stockmarketapp.presentation.stock_list

/**
 * Created by Martín Bove on 11/08/2023.
 * E-mail: mbove77@gmail.com
 */

sealed class StockListEvents {
    object Refresh: StockListEvents()

    data class OnSearchQueryChange(val query: String): StockListEvents()
}
