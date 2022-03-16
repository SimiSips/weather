package com.simphiwe.weatherapp.model


data class Weather(
    var description: String,
    val forecast: List<Forecast>,
    val temperature: String,
    val wind: String
)