package com.simphiwe.weatherapp.repository

import com.simphiwe.weatherapp.api.ApiService
import javax.inject.Inject

class WeatherRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getWeatherJohannesburg() = apiService.getWeatherJohannesburg()
    suspend fun getWeatherSandton() = apiService.getWeatherSandton()
    suspend fun getWeatherPretoria() = apiService.getWeatherPretoria()
}
