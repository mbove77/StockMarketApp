package com.martinbove.stockmarketapp.data.repository

import com.martinbove.stockmarketapp.BuildConfig
import com.martinbove.stockmarketapp.data.csv.CSVParser
import com.martinbove.stockmarketapp.data.local.StockDataBase
import com.martinbove.stockmarketapp.data.mapper.toDomain
import com.martinbove.stockmarketapp.data.mapper.toEntity
import com.martinbove.stockmarketapp.data.remote.StockApi
import com.martinbove.stockmarketapp.domain.model.StockList
import com.martinbove.stockmarketapp.domain.repository.StockRepository
import com.martinbove.stockmarketapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Martín Bove on 11/08/2023.
 * E-mail: mbove77@gmail.com
 */
@Singleton
class StockRepositoryImp @Inject constructor(
    private val api: StockApi,
    private val db: StockDataBase,
    private val stockListParser: CSVParser<StockList>
): StockRepository {

    private val dao = db.dao

    override suspend fun getStockList(fetchFromRemote: Boolean, query: String): Flow<Resource<List<StockList>>> {
       return flow {

           emit(Resource.Loading(true))
           val localStockList = dao.searchStockList(query)
           emit(Resource.Success(data = localStockList.map { it.toDomain() }))

           val isDbIsEmpty = localStockList.isEmpty() && query.isBlank()
           val shouldJustLoadFromCache = !isDbIsEmpty && !fetchFromRemote
           if (shouldJustLoadFromCache) {
               emit(Resource.Loading(false))
               return@flow
           }

           val remoteStockList = try {
               val response = api.getStockList(BuildConfig.API_KEY)
               stockListParser.parse(response.byteStream())
           } catch (e: IOException) {
              e.printStackTrace()
              emit(Resource.Error(message = e.message.toString()))
               null
           } catch (e: HttpException) {
               e.printStackTrace()
               emit(Resource.Error(message = e.message.toString()))
               null
           }

           remoteStockList?.let { stockList ->
               dao.deleteStockList()
               dao.insertStockList(stockList.map { it.toEntity() })
               emit(Resource.Success(dao.searchStockList("").map { it.toDomain() }))
               emit(Resource.Loading(false))
           }
       }
    }
}