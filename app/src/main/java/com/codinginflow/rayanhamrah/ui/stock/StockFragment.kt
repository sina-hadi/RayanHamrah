package com.codinginflow.rayanhamrah.ui.stock

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.viewModels
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.adapter.StockPagerAdapter
import com.codinginflow.rayanhamrah.databinding.FragmentStockBinding
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import com.codinginflow.rayanhamrah.util.NetworkResult
import com.codinginflow.rayanhamrah.util.formatDecimalSeparator
import com.codinginflow.rayanhamrah.viewmodel.StockViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs
import kotlin.math.roundToInt

@AndroidEntryPoint
class StockFragment : Fragment() {

    private lateinit var binding: FragmentStockBinding
    private val tabTitles = arrayListOf("Namad", "Nazer", "Open", "My", "Book", "Note")

    private val stockViewModel: StockViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stockViewModel.getStock()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStockBinding.inflate(layoutInflater)

        stockViewModel.stockResponse.observe(viewLifecycleOwner) { response ->
            if (response is NetworkResult.Success) {
                response.data?.let {
                    setProperties(it)
                }
            } else {
                Log.e("ABCD_Stock", response.message.toString())
            }
        }

        setUpTabLayoutWithViewPager()

        binding.personIcon2.updateLayoutParams<ConstraintLayout.LayoutParams> {
            horizontalBias = 0.5f
        }

        return binding.root
    }

    private fun setProperties(data: Stock) {
        setHeaderLayout(data)
        setPowerBuySell(data)
        setCustomProgressBar(data)
    }

    @SuppressLint("SetTextI18n")
    private fun setHeaderLayout(data: Stock) {
        binding.namadName.text = data.name
        binding.stockNumber2.text = data.transferVolume.formatDecimalSeparator()
        binding.stockPrice.text = data.currentPrice.toString()
        binding.stockPriceDiff.text = (data.currentPrice - data.yesterdayPrice).toString()
        binding.stockPriceEnd.text = data.endPrice.toString()
        binding.stockPriceEndDiff.text = (data.endPrice - data.yesterdayPrice).toString()

        if (data.currentPrice - data.yesterdayPrice >= 0) {
            binding.stockPriceDiff.setTextColor(Color.parseColor("#289C00"))
        } else {
            binding.stockPriceDiff.setTextColor(Color.parseColor("#E90F0F"))
        }

        if (data.endPrice - data.yesterdayPrice >= 0) {
            binding.stockPriceEndDiff.setTextColor(Color.parseColor("#289C00"))
        } else {
            binding.stockPriceEndDiff.setTextColor(Color.parseColor("#E90F0F"))
        }

        binding.stockPricePercent.text = "(${
            (abs(data.currentPrice.toFloat() / data.yesterdayPrice - 1) * 10000.0).roundToInt()
                .toFloat() / 100.0
        }%)"
        binding.stockPriceEndPercent.text = "(${
            (abs(data.endPrice.toFloat() / data.yesterdayPrice - 1) * 10000.0).roundToInt().toFloat() / 100.0
        }%)"

        if (data.currentPrice / data.yesterdayPrice - 1 >= 0) {
            binding.stockPricePercent.setTextColor(Color.parseColor("#289C00"))
        } else {
            binding.stockPricePercent.setTextColor(Color.parseColor("#E90F0F"))
        }

        if (data.endPrice / data.yesterdayPrice - 1 >= 0) {
            binding.stockPriceEndPercent.setTextColor(Color.parseColor("#289C00"))
        } else {
            binding.stockPriceEndPercent.setTextColor(Color.parseColor("#E90F0F"))
        }
    }

    private fun setPowerBuySell(data: Stock) {
        binding.personIcon.updateLayoutParams<ConstraintLayout.LayoutParams> {
            horizontalBias = 0.7f
        }
        binding.personIcon2.updateLayoutParams<ConstraintLayout.LayoutParams> {
            horizontalBias = 0.2f
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCustomProgressBar(data: Stock) {
        binding.customProgressBar.firstPrice = data.firstPrice
        binding.customProgressBar.lastPrice = data.currentPrice
        binding.customProgressBar.maxPrice = data.highestPrice
        binding.customProgressBar.minPrice = data.lowestPrice
        binding.customProgressBar.maxRange = data.highestRange
        binding.customProgressBar.minRange = data.lowestRange
        binding.customProgressBar.invalidate()
        binding.minPrice.text = data.lowestRange.toString()
        binding.maxPrice.text = data.highestRange.toString()

        binding.startPricePercent.text = "(${
            (abs(data.firstPrice.toFloat() / data.yesterdayPrice - 1) * 10000.0).roundToInt()
                .toFloat() / 100.0
        }%)"
        binding.endPricePercent.text = "(${
            (abs(data.currentPrice.toFloat() / data.yesterdayPrice - 1) * 10000.0).roundToInt()
                .toFloat() / 100.0
        }%)"
        if (data.firstPrice - data.yesterdayPrice >= 0) {
            binding.startPricePercent.setTextColor(Color.parseColor("#289C00"))
        } else {
            binding.startPricePercent.setTextColor(Color.parseColor("#E90F0F"))
        }
        if (data.currentPrice - data.yesterdayPrice >= 0) {
            binding.endPricePercent.setTextColor(Color.parseColor("#289C00"))
        } else {
            binding.endPricePercent.setTextColor(Color.parseColor("#E90F0F"))
        }

        binding.startPrice.text = data.firstPrice.toString()
        binding.endPrice.text = data.currentPrice.toString()
        binding.startPrice.updateLayoutParams<ConstraintLayout.LayoutParams> {
            horizontalBias = 1f - (data.firstPrice.toFloat() - data.lowestRange) / (data.highestRange - data.lowestRange)
        }
        binding.endPrice.updateLayoutParams<ConstraintLayout.LayoutParams> {
            horizontalBias = 1f - (data.currentPrice.toFloat() - data.lowestRange) / (data.highestRange - data.lowestRange)
        }
    }

    private fun setUpTabLayoutWithViewPager() {
        binding.viewPager.adapter = StockPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

        for (i in 0..6) {
            val textView: TextView =
                LayoutInflater.from(requireContext()).inflate(R.layout.tab_title, null) as TextView
            textView.textSize = 12f
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }
}