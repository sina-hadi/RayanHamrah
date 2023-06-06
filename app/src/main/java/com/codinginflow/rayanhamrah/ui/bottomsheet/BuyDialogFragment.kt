package com.codinginflow.rayanhamrah.ui.bottomsheet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.codinginflow.rayanhamrah.R
import com.fauji.commaseparated.widget.CommaSeparatedEditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BuyDialogFragment : BottomSheetDialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buy_dialog, container, false)


        view.findViewById<Button>(R.id.buyStockButton).setOnClickListener {
            val price = view.findViewById<CommaSeparatedEditText>(R.id.commaSeparatedEditText).getTextWithoutCommas().toLong()
            val volume = view.findViewById<CommaSeparatedEditText>(R.id.commaSeparatedEditText2).getTextWithoutCommas().toLong()
        }


        return view
    }
}