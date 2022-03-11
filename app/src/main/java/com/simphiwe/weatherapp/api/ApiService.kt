package com.simphiwe.weatherapp.api

import com.simphiwe.weatherapp.model.Weather
import com.simphiwe.weatherapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather/johannesburg")
    suspend fun getWeatherJohannesburg(): Response<Weather>

    @GET("weather/sandton")
    suspend fun getWeatherSandton(): Response<Weather>

    @GET("weather/pretoria")
    suspend fun getWeatherPretoria(): Response<Weather>

}