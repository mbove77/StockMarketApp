package com.martinbove.stockmarketapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Martín Bove on 09/08/2023.
 * E-mail: mbove77@gmail.com
 */
@Database(
    entities = [StockListEntity::class],
    version = 1
)
abstract class StockDataBase: RoomDatabase() {
    abstract val dao: StockDao
}