package com.codinginflow.rayanhamrah.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.codinginflow.rayanhamrah.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment : Fragment() {

    private lateinit var borsChart: TextView
    private lateinit var fraBorsChart: TextView
    private lateinit var otherChart: TextView
    private lateinit var navFragment: FragmentContainerView

    private var state: Int = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)

        navFragment = view.findViewById(R.id.navHostFragmentChart)
        borsChart = view.findViewById(R.id.borsChart)
        fraBorsChart = view.findViewById(R.id.fraBorsChart)
        otherChart = view.findViewById(R.id.otherChart)

        state = savedInstanceState?.getInt("STATE") ?: state

        setTheme(state)

        borsChart.setOnClickListener {
            if (state != 1) {
                transaction(1, state)
                state = 1
                setTheme(state)
            }
        }

        fraBorsChart.setOnClickListener {
            if (state != 2) {
                transaction(2, state)
                state = 2
                setTheme(state)
            }
        }

        otherChart.setOnClickListener {
            if (state != 3) {
                transaction(3, state)
                state = 3
                setTheme(state)
            }
        }

        return view
    }


    private fun transaction(i: Int, state: Int) {
        when (i) {
            1 -> {
                when (state) {
                    2 -> navFragment.findNavController()
                        .navigate(R.id.action_fraBorsChartFragment_to_borsChartFragment)
                    3 -> navFragment.findNavController()
                        .navigate(R.id.action_otherChartFragment_to_borsChartFragment)
                }
            }
            2 -> {
                when (state) {
                    1 -> navFragment.findNavController()
                        .navigate(R.id.action_borsChartFragment_to_fraBorsChartFragment)
                    3 -> navFragment.findNavController()
                        .navigate(R.id.action_otherChartFragment_to_fraBorsChartFragment)
                }
            }
            3 -> {
                when (state) {
                    1 -> navFragment.findNavController()
                        .navigate(R.id.action_borsChartFragment_to_otherChartFragment)
                    2 -> navFragment.findNavController()
                        .navigate(R.id.action_fraBorsChartFragment_to_otherChartFragment)
                }
            }
        }
    }

    private fun setTheme(position: Int) {
        when (position) {
            1 -> {
                borsChart.setBackgroundResource(R.drawable.selected_theme)
                fraBorsChart.setBackgroundResource(R.drawable.default_theme)
                otherChart.setBackgroundResource(R.drawable.default_theme)

                borsChart.setTextColor(resources.getColor(R.color.white))
                fraBorsChart.setTextColor(resources.getColor(R.color.gray))
                otherChart.setTextColor(resources.getColor(R.color.gray))
            }
            2 -> {
                borsChart.setBackgroundResource(R.drawable.default_theme)
                fraBorsChart.setBackgroundResource(R.drawable.selected_theme)
                otherChart.setBackgroundResource(R.drawable.default_theme)

                borsChart.setTextColor(resources.getColor(R.color.gray))
                fraBorsChart.setTextColor(resources.getColor(R.color.white))
                otherChart.setTextColor(resources.getColor(R.color.gray))
            }
            3 -> {
                borsChart.setBackgroundResource(R.drawable.default_theme)
                fraBorsChart.setBackgroundResource(R.drawable.default_theme)
                otherChart.setBackgroundResource(R.drawable.selected_theme)

                borsChart.setTextColor(resources.getColor(R.color.gray))
                fraBorsChart.setTextColor(resources.getColor(R.color.gray))
                otherChart.setTextColor(resources.getColor(R.color.white))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("STATE", state)
        super.onSaveInstanceState(outState)
    }

}