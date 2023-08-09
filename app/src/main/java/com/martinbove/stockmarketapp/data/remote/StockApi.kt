package com.martinbove.stockmarketapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Martín Bove on 09/08/2023.
 * E-mail: mbove77@gmail.com
 */

interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getStockList(
        @Query("apiKey") apiKey: String
    ): ResponseBody

    companion object {
        const val BASE_URL = "'https://www.alphavantage.co"
    }
}