package com.example.markiiimark.weatherapp.domain.command

import com.example.markiiimark.weatherapp.domain.datasource.ForecastProvider
import com.example.markiiimark.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long,
                             private val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {
    companion object {
        val DAYS = 7
    }
    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
}