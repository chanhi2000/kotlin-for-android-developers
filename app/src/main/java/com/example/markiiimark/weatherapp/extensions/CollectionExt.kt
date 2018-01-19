package com.example.markiiimark.weatherapp.extensions

fun <K, V : Any> Map<K, V?>.toVaragArray(): Array<out Pair<K, V>> =
        map {  Pair(it.key, it.value!!)  }.toTypedArray()