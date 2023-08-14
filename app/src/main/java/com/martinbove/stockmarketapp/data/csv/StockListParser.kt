package com.martinbove.stockmarketapp.data.csv

import com.martinbove.stockmarketapp.domain.model.Stock
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martín Bove on 11/08/2023.
 * E-mail: mbove77@gmail.com
 */
@Singleton
class StockListParser @Inject constructor(): CSVParser<Stock> {
    override suspend fun parse(stream: InputStream): List<Stock> {
        val csvReader = CSVReader(InputStreamReader(stream))

        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange =  line.getOrNull(2)

                    Stock(
                        name = name?: return@mapNotNull null,
                        symbol = symbol?: return@mapNotNull null,
                        exchange = exchange?: return@mapNotNull null
                    )
                }
        }.also { csvReader.close() }
    }
}