package com.martinbove.stockmarketapp.data.csv

import java.io.InputStream

/**
 * Created by Martín Bove on 11/08/2023.
 * E-mail: mbove77@gmail.com
 */

interface CSVParser<T>  {
    suspend fun parse(stream: InputStream): List<T>
}