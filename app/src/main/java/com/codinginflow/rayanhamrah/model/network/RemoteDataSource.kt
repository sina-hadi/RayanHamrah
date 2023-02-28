package com.codinginflow.rayanhamrah.model.network

import com.codinginflow.rayanhamrah.model.data.stock.Stock
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val stockRecipesApi: StockRecipesApi
) {

    suspend fun stockRecipes(): Response<Stock> {
        return stockRecipesApi.getStock()
    }

}