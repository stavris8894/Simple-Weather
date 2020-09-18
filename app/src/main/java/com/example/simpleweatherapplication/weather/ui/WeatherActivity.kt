package com.example.simpleweatherapplication.weather.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.simpleweatherapplication.databinding.ActivityMainBinding
import com.example.simpleweatherapplication.utils.BaseActivity
import com.example.simpleweatherapplication.utils.GooglePlaceFragment
import com.example.simpleweatherapplication.utils.extensions.replaceFragment
import com.example.simpleweatherapplication.utils.fragment.viewBinding
import com.example.simpleweatherapplication.weather.viewmodels.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(WeatherFragment())
        GooglePlaceFragment(supportFragmentManager).getPlace({ cityName, shortName ->
            weatherViewModel.getCityWeatherData(cityName, shortName)
        }) {
            Log.d(TAG, "GooglePlaceError: ${it.statusMessage}")
        }
    }


    companion object {

        private val TAG = WeatherActivity::class.java.simpleName

        fun startActivity(context: Context) {
            Intent(context, WeatherActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }
}