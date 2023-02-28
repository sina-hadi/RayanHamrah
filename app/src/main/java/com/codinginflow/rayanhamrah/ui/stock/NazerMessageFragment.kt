package com.codinginflow.rayanhamrah.ui.stock

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import com.codinginflow.rayanhamrah.util.NetworkResult
import com.codinginflow.rayanhamrah.viewmodel.StockViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NazerMessageFragment : Fragment() {
    
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nazer_message, container, false)


        return view
    }
}