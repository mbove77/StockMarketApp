package com.martinbove.stockmarketapp.domain.repository

import com.martinbove.stockmarketapp.domain.model.Stock
import com.martinbove.stockmarketapp.utils.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Martín Bove on 10/08/2023.
 * E-mail: mbove77@gmail.com
 */

interface StockRepository {
    suspend fun getStockList(fetchFromRemote: Boolean, query: String): Flow<Resource<List<Stock>>>
}