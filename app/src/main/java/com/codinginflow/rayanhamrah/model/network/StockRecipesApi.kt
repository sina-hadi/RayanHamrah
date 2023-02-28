package com.codinginflow.rayanhamrah.model.network

import com.codinginflow.rayanhamrah.model.data.stock.Stock
import retrofit2.Response
import retrofit2.http.GET

interface StockRecipesApi {

    @GET("/10001")
    suspend fun getStock() : Response<Stock>

}