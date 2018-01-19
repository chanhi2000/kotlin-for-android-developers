package com.example.markiiimark.weatherapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.markiiimark.weatherapp.R
import com.example.markiiimark.weatherapp.domain.model.Forecast
import com.example.markiiimark.weatherapp.domain.model.ForecastList
import com.example.markiiimark.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.find
import org.w3c.dom.Text

class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: ForecastListAdapter.OnItemClickListener):
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val retView = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(retView, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int = weekForecast.dailyForecast.size

    class ViewHolder(val view: View,
                     private val itemClick: OnItemClickListener):
            RecyclerView.ViewHolder(view) {
        private val iconView by lazy {  view.find(R.id.icon) as ImageView  }
        private val dateView by lazy {  view.find(R.id.dateTextView) as TextView  }
        private val descriptionView by lazy {  view.find(R.id.descriptionTextView) as TextView  }
        private val maxTemperatureView by lazy {  view.find(R.id.maxTemperatureTextView) as TextView  }
        private val minTemperatureView by lazy {  view.find(R.id.minTemperatureTextView) as TextView  }

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx)
                        .load(iconUrl)
                        .into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "${high.toString()}"
                minTemperatureView.text = "${low.toString()}"
                itemView.setOnClickListener{  itemClick(this)  }
            }
        }

    }

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }
}