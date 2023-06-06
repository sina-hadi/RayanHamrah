package com.codinginflow.rayanhamrah.util

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null
) {

    class Success<T>(data: T?, statusCode: Int? = null) :
        NetworkResult<T>(data = data, statusCode = statusCode)

    class Error<T>(message: String?, data: T? = null, statusCode: Int? = null) :
        NetworkResult<T>(data = data, message = message, statusCode = statusCode)

    class Loading<T> : NetworkResult<T>()

}