package com.codinginflow.rayanhamrah.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.model.data.response.LoginRequest
import com.codinginflow.rayanhamrah.ui.MainActivity
import com.codinginflow.rayanhamrah.util.NetworkResult
import com.codinginflow.rayanhamrah.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.http.HTTP_BAD_REQUEST

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            var userName: String = findViewById<EditText>(R.id.userName).text.toString()
            var password: String = findViewById<EditText>(R.id.password).text.toString()

            loginViewModel.login(LoginRequest(userName = userName, password = password))
        }

        loginViewModel.loginResponse.observe(this) { response ->
            if (response is NetworkResult.Success) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else if (response.statusCode == HTTP_BAD_REQUEST) {
                Toast.makeText(this, "Username or Password is incorrect!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Unknown Error contact us!", Toast.LENGTH_LONG).show()
            }
        }

    }
}