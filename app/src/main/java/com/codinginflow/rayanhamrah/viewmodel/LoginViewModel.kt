package com.codinginflow.rayanhamrah.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codinginflow.rayanhamrah.model.data.response.APIResponse
import com.codinginflow.rayanhamrah.model.data.response.LoginRequest
import com.codinginflow.rayanhamrah.model.network.Repository
import com.codinginflow.rayanhamrah.util.Constants.SHARED_PREF_FILE
import com.codinginflow.rayanhamrah.util.Constants.TOKEN
import com.codinginflow.rayanhamrah.util.HandleResponse
import com.codinginflow.rayanhamrah.util.NetworkResult
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: Repository
) : ViewModel() {

    var loginResponse: MutableLiveData<NetworkResult<APIResponse>> = MutableLiveData()

    private val sharedPreferences: SharedPreferences = getApplication(context).getSharedPreferences(SHARED_PREF_FILE, Context.MODE_PRIVATE)

    fun login(login : LoginRequest) = viewModelScope.launch {
        loginSafeCall(login)
    }

    private suspend fun loginSafeCall(login : LoginRequest) {
        try {
            val response = repository.remote.loginRequest(login)
            loginResponse.value = HandleResponse(response).handleResponse

            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString(TOKEN, response.body()?.result?.token)
            editor.apply()
            editor.commit()
        } catch (e: Exception) {
            loginResponse.value = NetworkResult.Error("Stock not found.(LoginViewModel) $e")
        }
    }
}