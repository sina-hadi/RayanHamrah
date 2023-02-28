package com.codinginflow.rayanhamrah.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import com.codinginflow.rayanhamrah.model.network.Repository
import com.codinginflow.rayanhamrah.util.HandleResponse
import com.codinginflow.rayanhamrah.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var stockResponse: MutableLiveData<NetworkResult<Stock>> = MutableLiveData()

    fun getStock() = viewModelScope.launch {
        getStockSafeCall()
    }

    private suspend fun getStockSafeCall() {
        try {
            val response = repository.remote.stockRecipes()
            stockResponse.value = HandleResponse(response).handleResponse
            Log.e("ABCD",stockResponse.value.toString())
        } catch (e: Exception) {
            stockResponse.value = NetworkResult.Error("Stock not found. $e")
        }
    }

}