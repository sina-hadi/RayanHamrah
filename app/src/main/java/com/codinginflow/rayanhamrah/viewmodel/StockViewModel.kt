package com.codinginflow.rayanhamrah.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import com.codinginflow.rayanhamrah.model.network.Repository
import com.codinginflow.rayanhamrah.util.Constants.SHARED_PREF_FILE
import com.codinginflow.rayanhamrah.util.Constants.TOKEN
import com.codinginflow.rayanhamrah.util.HandleResponse
import com.codinginflow.rayanhamrah.util.NetworkResult
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: Repository
) : ViewModel() {

    var stockResponse: MutableLiveData<NetworkResult<Stock>> = MutableLiveData()

    private val sharedPreferences: SharedPreferences = Contexts.getApplication(context)
        .getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

    fun getStock() = viewModelScope.launch {
        getStockSafeCall()
    }

    private suspend fun getStockSafeCall() {
        try {
            val sharedToken = sharedPreferences.getString(TOKEN, "EMPTY_TOKEN")
            val response = repository.remote.stockRecipes("Bearer $sharedToken")
            stockResponse.value = HandleResponse(response).handleResponse
            Log.e("ABCD",stockResponse.value.toString())
        } catch (e: Exception) {
            stockResponse.value = NetworkResult.Error("Stock not found. $e")
        }
    }

}