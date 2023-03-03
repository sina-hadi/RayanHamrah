package com.codinginflow.rayanhamrah.model.data.stock

import com.google.gson.annotations.SerializedName

data class Stock(
    @SerializedName("baseVolume")
    val baseVolume: Int,
    @SerializedName("buyOrders")
    val buyOrders: List<Order>,
    @SerializedName("currentPrice")
    val currentPrice: Int,
    @SerializedName("EndPrice")
    val endPrice: Int,
    @SerializedName("firstPrice")
    val firstPrice: Int,
    @SerializedName("fullName")
    val fullName: String,
    @SerializedName("highestPrice")
    val highestPrice: Int,
    @SerializedName("highestRange")
    val highestRange: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastOrderTime")
    val lastOrderTime: String,
    @SerializedName("legalBuyCount")
    val legalBuyCount: Int,
    @SerializedName("legalBuyVolume")
    val legalBuyVolume: Int,
    @SerializedName("legalSellCount")
    val legalSellCount: Int,
    @SerializedName("legalSellVolume")
    val legalSellVolume: Int,
    @SerializedName("lowestPrice")
    val lowestPrice: Int,
    @SerializedName("lowestRange")
    val lowestRange: Int,
    @SerializedName("marketType")
    val marketType: String,
    @SerializedName("marketValue")
    val marketValue: Long,
    @SerializedName("monthlyReturn")
    val monthlyReturn: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("naturalBuyCount")
    val naturalBuyCount: Int,
    @SerializedName("naturalBuyVolume")
    val naturalBuyVolume: Int,
    @SerializedName("naturalSellCount")
    val naturalSellCount: Int,
    @SerializedName("naturalSellVolume")
    val naturalSellVolume: Int,
    @SerializedName("orderRange")
    val orderRange: Int,
    @SerializedName("priceChange")
    val priceChange: Int,
    @SerializedName("sellOrders")
    val sellOrders: List<Order>,
    @SerializedName("threeMonthReturn")
    val threeMonthReturn: Double,
    @SerializedName("tomorrowHighestRange")
    val tomorrowHighestRange: Int,
    @SerializedName("tomorrowLowestRange")
    val tomorrowLowestRange: Int,
    @SerializedName("transferVolume")
    val transferVolume: Int,
    @SerializedName("volumeChange")
    val volumeChange: Int,
    @SerializedName("stockInfo")
    val stockInfo: String,
    @SerializedName("weekHighestRange")
    val weekHighestRange: Int,
    @SerializedName("weekLowestRange")
    val weekLowestRange: Int,
    @SerializedName("yearHighestRange")
    val yearHighestRange: Int,
    @SerializedName("yearLowestRange")
    val yearLowestRange: Int,
    @SerializedName("yesterdayPrice")
    val yesterdayPrice: Int
)