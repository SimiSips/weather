package com.simphiwe.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simphiwe.weatherapp.model.Weather
import com.simphiwe.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModelPretoria
@Inject
constructor(private val repository: WeatherRepository) : ViewModel() {

    private val _response = MutableLiveData<Weather>()
    val weatherResponsePretoria: LiveData<Weather>
        get() = _response


    init {
        getWeatherPretoria()
    }

    private fun getWeatherJohannesburg() = viewModelScope.launch {
        repository.getWeatherJohannesburg().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getWeather Error: ${response.code()}")
            }
        }
    }
    private fun getWeatherSandton() = viewModelScope.launch {
        repository.getWeatherSandton().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getWeather Error: ${response.code()}")
            }
        }
    }

    private fun getWeatherPretoria() = viewModelScope.launch {
        repository.getWeatherPretoria().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("tag", "getWeather Error: ${response.code()}")
            }
        }
    }


}













