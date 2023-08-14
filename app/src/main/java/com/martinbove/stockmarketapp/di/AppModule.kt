package com.martinbove.stockmarketapp.di

import android.app.Application
import androidx.room.Room
import com.martinbove.stockmarketapp.data.local.StockDataBase
import com.martinbove.stockmarketapp.data.remote.StockApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


/**
 * Created by Martín Bove on 14/08/2023.
 * E-mail: mbove77@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDb(app: Application): StockDataBase {
        return Room.databaseBuilder(
            app,
            StockDataBase::class.java,
            "stockapp.db"
        ).build()

    }
}