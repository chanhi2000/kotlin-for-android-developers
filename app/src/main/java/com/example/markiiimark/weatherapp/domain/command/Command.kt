package com.example.markiiimark.weatherapp.domain.command

public interface Command<T> {
    fun execute(): T
}