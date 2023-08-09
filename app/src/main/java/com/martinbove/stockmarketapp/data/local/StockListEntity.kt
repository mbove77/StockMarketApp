package com.martinbove.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Martín Bove on 09/08/2023.
 * E-mail: mbove77@gmail.com
 */
@Entity
data class StockListEntity(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val symbol: String,
    val exchange: String
)
