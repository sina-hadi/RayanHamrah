package com.codinginflow.rayanhamrah.model.data.response


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("errorMessages")
    val errorMessages: List<Any?>,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("result")
    val result: Result?,
    @SerializedName("statusCode")
    val statusCode: Int
)