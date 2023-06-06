package com.codinginflow.rayanhamrah.model.data.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("userName")
    val userName: String
)