package com.codinginflow.rayanhamrah.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.rayanhamrah.model.data.OrdersType
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.model.data.User
import com.codinginflow.rayanhamrah.model.data.stock.Stock
import org.w3c.dom.Text
import kotlin.math.abs
import kotlin.math.roundToInt

class StockOrdersAdapter(private val type: OrdersType, private val data: Stock) :
    RecyclerView.Adapter<StockOrdersAdapter.MyViewHolder>() {

    private var highestVolume = 0

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        for (i in 0..4) {
            if (highestVolume < data.buyOrders[i].volume) {
                highestVolume = data.buyOrders[i].volume
            }
            if (highestVolume < data.sellOrders[i].volume) {
                highestVolume = data.sellOrders[i].volume
            }
        }

        return if (type == OrdersType.BUY) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.stock_orders_buy, parent, false)
            MyViewHolder(view)
        } else {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.stock_orders_sell, parent, false)
            MyViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (type == OrdersType.BUY) {
            holder.itemView.findViewById<TextView>(R.id.stockCountNumber).text =
                data.buyOrders[position].volume.toString()
            (holder.itemView.findViewById<View>(R.id.color_line).layoutParams as LinearLayout.LayoutParams).weight =
                data.buyOrders[position].volume.toFloat() / highestVolume
            (holder.itemView.findViewById<View>(R.id.default_line).layoutParams as LinearLayout.LayoutParams).weight =
                1f - (data.buyOrders[position].volume.toFloat() / highestVolume)
            holder.itemView.findViewById<TextView>(R.id.peopleNumber).text = data.buyOrders[position].count.toString()
            holder.itemView.findViewById<TextView>(R.id.stockPrice2).text = data.buyOrders[position].price.toString()
            holder.itemView.findViewById<TextView>(R.id.stockPercent2).text = setPercentage(holder, position)
        } else {
            holder.itemView.findViewById<TextView>(R.id.stockCountNumber).text =
                data.sellOrders[position].volume.toString()
            (holder.itemView.findViewById<View>(R.id.color_line).layoutParams as LinearLayout.LayoutParams).weight =
                data.sellOrders[position].volume.toFloat() / highestVolume
            (holder.itemView.findViewById<View>(R.id.default_line).layoutParams as LinearLayout.LayoutParams).weight =
                1f - (data.sellOrders[position].volume.toFloat() / highestVolume)
            holder.itemView.findViewById<TextView>(R.id.peopleNumber).text = data.sellOrders[position].count.toString()
            holder.itemView.findViewById<TextView>(R.id.stockPrice2).text = data.sellOrders[position].price.toString()
            holder.itemView.findViewById<TextView>(R.id.stockPercent2).text = setPercentage(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    @SuppressLint("ResourceAsColor")
    private fun setPercentage(holder: MyViewHolder, position: Int) : String {
        val percent = if (type == OrdersType.BUY) {
            data.buyOrders[position].price.toFloat() / data.yesterdayPrice.toFloat() - 1
        } else {
            data.sellOrders[position].price.toFloat() / data.yesterdayPrice.toFloat() - 1
        }
        return if (percent >= 0) {
            holder.itemView.findViewById<TextView>(R.id.stockPercent2).setTextColor(Color.parseColor("#289C00"))
            "(${(abs(percent) * 10000.0).roundToInt().toFloat() / 100.0}%)"
        } else {
            holder.itemView.findViewById<TextView>(R.id.stockPercent2).setTextColor(Color.parseColor("#E90F0F"))
            "(${(abs(percent) * 10000.0).roundToInt().toFloat() / 100.0}%)"
        }
    }

}