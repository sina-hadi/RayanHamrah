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
            NetworkResult.Success(data = response.body(), statusCode = response.code())
        }

        else -> {
            NetworkResult.Error(
                message = response.message(),
                data = response.body(),
                statusCode = response.code()
            )
        }
    }
}