package com.example.markiiimark.weatherapp.domain.command

import com.example.markiiimark.weatherapp.data.ForecastRequest
import com.example.markiiimark.weatherapp.domain.mapper.ForecastDataMapper
import com.example.markiiimark.weatherapp.domain.model.ForecastList

class RequestForecastCommand(val zipCode:String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}