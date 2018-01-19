package com.example.markiiimark.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.markiiimark.weatherapp.R
import com.example.markiiimark.weatherapp.domain.model.Forecast
import com.example.markiiimark.weatherapp.domain.model.ForecastList
import com.example.markiiimark.weatherapp.extensions.ctx
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.item_forecast.view.*
import java.text.DateFormat
import java.util.*

class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: ((Forecast) -> Unit)):
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val retView = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(retView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    class ViewHolder(val view: View,
                     private val itemClick: ((Forecast) -> Unit)):
            RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.dateTextView.text = convertDate(date)
                itemView.descriptionTextView.text = description
                itemView.maxTemperatureTextView.text = "${high.toString()}"
                itemView.minTemperatureTextView.text = "${low.toString()}"
                itemView.setOnClickListener{  itemClick(this)  }
            }
        }

        private fun convertDate(date: Long): String {
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            return df.format(date)
        }

    }
}