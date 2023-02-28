package com.codinginflow.rayanhamrah.ui.stock

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.util.NetworkResult
import com.codinginflow.rayanhamrah.viewmodel.StockViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OpenOrderFragment : Fragment() {

    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_open_order, container, false)



//        (view.findViewById<View>(R.id.redLine).layoutParams as LinearLayout.LayoutParams).weight = 0.3f


        return view
    }
}