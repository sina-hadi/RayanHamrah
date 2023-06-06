package com.codinginflow.rayanhamrah.model.data.response


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)