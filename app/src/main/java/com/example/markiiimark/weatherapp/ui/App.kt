package com.example.markiiimark.weatherapp.ui

import android.app.Application
import com.example.markiiimark.weatherapp.extensions.DelegateExt

class App: Application() {
    companion object {
        var instance: App by DelegateExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}