package com.example.markiiimark.weatherapp.data.db

import com.example.markiiimark.weatherapp.domain.datasource.ForecastDataSource
import com.example.markiiimark.weatherapp.domain.model.ForecastList
import com.example.markiiimark.weatherapp.extensions.clear
import com.example.markiiimark.weatherapp.extensions.parseList
import com.example.markiiimark.weatherapp.extensions.parseOpt
import com.example.markiiimark.weatherapp.extensions.toVaragArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long,
                                          date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList {  DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt {  CityForecast(HashMap(it), dailyForecast)}

        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVaragArray())
            dailyForecast.forEach {  insert(DayForecastTable.NAME, *it.map.toVaragArray()) }
        }
    }
}
