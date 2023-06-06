package com.codinginflow.rayanhamrah.model.network

import com.codinginflow.rayanhamrah.model.data.response.APIResponse
import com.codinginflow.rayanhamrah.model.data.response.LoginRequest
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val stockRecipesApi: StockRecipesApi
) {

    suspend fun stockRecipes(token: String): Response<Stock> {
        return stockRecipesApi.getStock(token)
    }

    suspend fun loginRequest(login : LoginRequest): Response<APIResponse> {
        return stockRecipesApi.loginRequest(login)
    }

}