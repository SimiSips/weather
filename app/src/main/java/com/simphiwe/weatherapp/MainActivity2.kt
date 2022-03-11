package com.simphiwe.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.simphiwe.weatherapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main2)

        val johannesburg = findViewById<CardView>(R.id.cityJohannesburg)
        val sandton = findViewById<CardView>(R.id.citySandton)
        val pretoria = findViewById<CardView>(R.id.cityPretoria)

        johannesburg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        sandton.setOnClickListener {
            val intent = Intent(this, SandtonActivity::class.java)
            startActivity(intent)
        }

        pretoria.setOnClickListener {
            val intent = Intent(this, PretoriaActivity::class.java)
            startActivity(intent)
        }
    }
}