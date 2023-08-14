package com.martinbove.stockmarketapp.data.mapper

import com.martinbove.stockmarketapp.data.local.StockListEntity
import com.martinbove.stockmarketapp.domain.model.Stock

/**
 * Created by Martín Bove on 09/08/2023.
 * E-mail: mbove77@gmail.com
 */
fun StockListEntity.toDomain(): Stock {
    return Stock(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun Stock.toEntity(): StockListEntity {
    return StockListEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}