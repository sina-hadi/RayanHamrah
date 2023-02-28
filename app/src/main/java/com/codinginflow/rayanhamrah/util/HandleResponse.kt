package com.codinginflow.rayanhamrah.util

import retrofit2.Response
import javax.inject.Inject

class HandleResponse<T> @Inject constructor(
    response: Response<T>
) {
    val handleResponse: NetworkResult<T> = when {
        response.message().toString().contains("timeout") -> {
            NetworkResult.Error("Timeout")
        }
        response.isSuccessful -> {
            val stock = response.body()
            NetworkResult.Success(stock!!)
        }
        else -> {
            NetworkResult.Error(response.message())
        }
    }
}