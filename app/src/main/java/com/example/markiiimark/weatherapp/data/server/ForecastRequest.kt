package com.example.markiiimark.weatherapp.data.server

import com.google.gson.Gson
import java.net.URL

class ForecastRequest(val zipCode: Long) {
    companion object {
        private val APP_ID = "d4a653e3a82730702cc02e7cdbbd213e"
        private val BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "${BASE_URL}&APPID=${APP_ID}&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}