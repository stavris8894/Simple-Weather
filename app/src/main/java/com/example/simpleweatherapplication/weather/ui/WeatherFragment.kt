package com.example.simpleweatherapplication.weather.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.databinding.MainPageFragmentBinding
import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.utils.EventObserver
import com.example.simpleweatherapplication.utils.adapters.WeatherAdapter
import com.example.simpleweatherapplication.utils.extensions.hasId
import com.example.simpleweatherapplication.utils.extensions.showToast
import com.example.simpleweatherapplication.utils.fragment.viewBinding
import com.example.simpleweatherapplication.weather.datasource.WeatherActionsItem
import com.example.simpleweatherapplication.weather.viewmodels.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WeatherFragment : Fragment(R.layout.main_page_fragment) {

    private val binding by viewBinding(MainPageFragmentBinding::bind)

    private val recycleViewAdapter = WeatherAdapter().apply {
        listener = { item ->
            when {
                item.hasId(WeatherActionsItem.SHOW_DETAILS) -> {
                    detailsDialogFragment = WeatherDetailsDialogFragment.newInstance((item.data as WeatherData).cityName)
                    detailsDialogFragment.show(childFragmentManager, WeatherDetailsDialogFragment.TAG)
                }
            }
        }
        removelistener = { item ->
            weatherViewModel.removeWeatherData((item.data) as WeatherData)
        }
    }

    private val weatherViewModel: WeatherViewModel by sharedViewModel()

    private lateinit var detailsDialogFragment: WeatherDetailsDialogFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureElements()
        configureObservers()
    }

    private fun configureElements() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            weatherViewModel.refreshData()
        }
        binding.recyclerView.adapter = recycleViewAdapter
    }

    private fun configureObservers() {
        weatherViewModel.recycleViewItems.observe(viewLifecycleOwner, EventObserver {
            recycleViewAdapter.submitList(it.toList())
        })

        weatherViewModel.showProgressBar.observe(viewLifecycleOwner, EventObserver {
            binding.swipeRefreshLayout.isRefreshing = it
        })

        weatherViewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            showToast(it)
        })
    }
}