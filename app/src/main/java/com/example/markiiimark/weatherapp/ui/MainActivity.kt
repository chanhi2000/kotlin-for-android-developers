package com.example.markiiimark.weatherapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.markiiimark.weatherapp.R
import com.example.markiiimark.weatherapp.data.Request
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    companion object {
        val baseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?"
    }
    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thu 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"

    )
    private val forecaseList by lazy { find(R.id.forecastList) as RecyclerView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecaseList.layoutManager = LinearLayoutManager(this)
        forecaseList.adapter = ForecastListAdapter(items)

        val url = "${baseUrl}APPID=d4a653e3a82730702cc02e7cdbbd213e&q=94043&mode=json&units=metric&cnt=7"

        doAsync {
            Request(url).run()
            uiThread { longToast("Request performed") }
        }
    }
}
