package com.example.simpleweatherapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleweatherapplication.weather.ui.WeatherActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        WeatherActivity.startActivity(this)
        finish()
    }

}