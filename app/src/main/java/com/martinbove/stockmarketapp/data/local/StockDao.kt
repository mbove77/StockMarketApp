package com.martinbove.stockmarketapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Martín Bove on 09/08/2023.
 * E-mail: mbove77@gmail.com
 */
@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockList(stockList: List<StockListEntity>)

    @Query("DELETE From StockListEntity")
    suspend fun deleteStockList()

    @Query("""
        SELECT * FROM stocklistentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR UPPER(:query) == symbol
    """)
    suspend fun searchStockList(query: String): List<StockListEntity>
}