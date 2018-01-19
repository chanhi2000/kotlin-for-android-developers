package com.example.markiiimark.weatherapp.domain.command

import com.example.markiiimark.weatherapp.data.server.ForecastRequest
import com.example.markiiimark.weatherapp.domain.mapper.ForecastDataMapper
import com.example.markiiimark.weatherapp.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}