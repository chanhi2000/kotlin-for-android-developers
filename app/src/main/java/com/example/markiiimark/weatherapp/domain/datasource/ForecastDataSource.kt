package com.example.markiiimark.weatherapp.domain.datasource

import com.example.markiiimark.weatherapp.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long,
                                 date: Long) : ForecastList?
}