package com.simphiwe.weatherapp

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.simphiwe.weatherapp.databinding.ActivityMainBinding
import com.simphiwe.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.simphiwe.weatherapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*private fun isLocationPermissionGranted(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                ///requestCode
            )
            false
        } else {
            true
        }
    }*/

    private lateinit var binding: ActivityMainBinding
    val viewModel: WeatherViewModel by viewModels()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val TAG = "MainActivity"
    private lateinit var geocoder: Geocoder
    private lateinit var longitudeTxt: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        geocoder = Geocoder(this, Locale.getDefault())
        val btn = findViewById<Button>(R.id.button5)

        val backBtn = findViewById<Button>(R.id.backBtn)

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        getLastLocation()

        viewModel.weatherResponse.observe(this) { weather ->

            binding.apply {
                tvCityName.text = "Johannesburg"
                tvDescription.text = weather.description
                tvTemperature.text = weather.temperature
                btn.setOnClickListener {
                    tvDescription.visibility = View.VISIBLE
                    cardview.visibility = View.VISIBLE
                }

                tvWind.text = weather.wind

                val forecast = weather.forecast
                tvForecast1.text = "${forecast[0].temperature}/ ${forecast[0].wind}"
                tvForecast2.text = "${forecast[1].temperature}/ ${forecast[1].wind}"
                tvForecast3.text = "${forecast[2].temperature}/ ${forecast[2].wind}"

            }

        }
    }

    private fun getLastLocation() {
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)

        }

        val lastLocation = fusedLocationProviderClient.lastLocation



        lastLocation.addOnSuccessListener {
            if (it != null) {

                Log.d(TAG, "getLastLocation: Latitude is ${it.latitude}")
                Log.d(TAG, "getLastLocation: Longitude is ${it.longitude}")

                val address = geocoder.getFromLocation(it.latitude, it.longitude, 1)

                Log.d(TAG, "getLastLocation: ${address[0].getAddressLine(0)}")
                Log.d(TAG, "getLastLocation: ${address[0].locality}")

            }
        }

        lastLocation.addOnFailureListener {
            Log.d(TAG, "getLastLocation: Location cannot be traced")
        }
    }
}