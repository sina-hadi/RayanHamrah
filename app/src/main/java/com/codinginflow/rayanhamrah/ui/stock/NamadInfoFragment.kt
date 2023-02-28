package com.codinginflow.rayanhamrah.ui.stock

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.rayanhamrah.model.data.OrdersType
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.adapter.StockOrdersAdapter
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import com.codinginflow.rayanhamrah.util.NetworkResult
import com.codinginflow.rayanhamrah.viewmodel.StockViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamadInfoFragment : Fragment() {

    private val stockViewModel: StockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stockViewModel.getStock()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_namad_info, container, false)

        stockViewModel.stockResponse.observe(viewLifecycleOwner) { response ->
            if (response is NetworkResult.Success) {
                response.data?.let {
                    setRecyclerViews(view, it)
                }
            } else {
                Log.e("ABCD_Namad", response.message.toString())
            }
        }

//        (view.findViewById<View>(R.id.redLine).layoutParams as LinearLayout.LayoutParams).weight = 0.3f

        return view
    }

    @SuppressLint("CutPasteId")
    private fun setRecyclerViews(view: View, data: Stock) {
        view.findViewById<RecyclerView>(R.id.recyclerView1).layoutManager =
            LinearLayoutManager(requireContext())
        view.findViewById<RecyclerView>(R.id.recyclerView1).adapter = StockOrdersAdapter(
            OrdersType.SELL,
            data
        )

        view.findViewById<RecyclerView>(R.id.recyclerView2).layoutManager =
            LinearLayoutManager(requireContext())
        view.findViewById<RecyclerView>(R.id.recyclerView2).adapter = StockOrdersAdapter(
            OrdersType.BUY,
            data
        )
    }
}