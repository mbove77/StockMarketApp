package com.martinbove.stockmarketapp.di

import com.martinbove.stockmarketapp.data.csv.CSVParser
import com.martinbove.stockmarketapp.data.csv.StockListParser
import com.martinbove.stockmarketapp.data.repository.StockRepositoryImp
import com.martinbove.stockmarketapp.domain.model.Stock
import com.martinbove.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Martín Bove on 14/08/2023.
 * E-mail: mbove77@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindStockParser(stockListParser: StockListParser): CSVParser<Stock>

    @Binds
    @Singleton
    abstract fun bindStockRepository(stockRepositoryImp: StockRepositoryImp): StockRepository

}