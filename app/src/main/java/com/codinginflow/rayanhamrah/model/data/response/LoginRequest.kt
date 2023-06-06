package com.codinginflow.rayanhamrah.model.data.response

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("userName")
    val userName: String
)