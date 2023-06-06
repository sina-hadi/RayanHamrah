package com.codinginflow.rayanhamrah.model.network

import com.codinginflow.rayanhamrah.model.data.response.APIResponse
import com.codinginflow.rayanhamrah.model.data.response.LoginRequest
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface StockRecipesApi {

    @GET("/api/v1/VillaAPI/Stock")
    suspend fun getStock(@Header("Authorization") token: String) : Response<Stock>

    @POST("/api/v1/UsersAuth/login")
    suspend fun loginRequest(@Body login : LoginRequest) : Response<APIResponse>

}