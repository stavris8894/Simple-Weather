package com.example.simpleweatherapplication.weather.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.simpleweatherapplication.databinding.ActivityMainBinding
import com.example.simpleweatherapplication.utils.BaseActivity
import com.example.simpleweatherapplication.utils.extensions.replaceFragment
import com.example.simpleweatherapplication.utils.fragment.viewBinding

class WeatherActivity : BaseActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        replaceFragment(WeatherFragment())
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