package com.codinginflow.rayanhamrah.ui.bottomsheet

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.codinginflow.rayanhamrah.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyDialogFragment : BottomSheetDialogFragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buy_dialog, container, false)

        view.findViewById<TextInputLayout>(R.id.rootTextInput).setStartIconOnClickListener {
            view.findViewById<TextInputLayout>(R.id.rootTextInput).setEndIconDrawable(R.drawable.ic_lock)
            view.findViewById<TextInputLayout>(R.id.rootTextInput).endIconDrawable?.setTint(Color.BLUE)
            view.findViewById<EditText>(R.id.etOrderPrice).focusable = View.NOT_FOCUSABLE
        }



        return view
    }
}