package com.codinginflow.rayanhamrah.model.data.stock

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("count")
    val count: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("volume")
    val volume: Int
)