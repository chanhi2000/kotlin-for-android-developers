package com.example.markiiimark.weatherapp.ui.acitivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.markiiimark.weatherapp.R
import com.example.markiiimark.weatherapp.domain.command.RequestForecastCommand
import com.example.markiiimark.weatherapp.ui.adapter.ForecastListAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.activity_main.forecastList

class MainActivity : AppCompatActivity() {
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thu 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"

    )
//    private val forecaseList by lazy { find(R.id.forecastList) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastList.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                forecastList.adapter = ForecastListAdapter(result) { forecast ->
                    toast(forecast.description)
                }
            }
        }
    }
}
