package com.example.simpleweatherapplication.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.databinding.MainPageFragmentBinding
import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.state.actions.WeatherAction
import com.example.simpleweatherapplication.state.viewstates.WeatherViewState
import com.example.simpleweatherapplication.utils.GooglePlaceFragment
import com.example.simpleweatherapplication.utils.adapters.WeatherAdapter
import com.example.simpleweatherapplication.utils.extensions.hasId
import com.example.simpleweatherapplication.utils.fragment.viewBinding
import com.example.simpleweatherapplication.weather.datasource.WeatherActionsItem
import com.example.simpleweatherapplication.weather.viewmodels.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment(R.layout.main_page_fragment) {

    private val binding by viewBinding(MainPageFragmentBinding::bind)

    private val weatherViewModel: WeatherViewModel by viewModel()

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
            weatherViewModel.dispatch(WeatherAction.RemoveWeather(item.data as WeatherData))
        }
    }

    private val mWeatherFragmentObserver = Observer(this::handleViewState)

    private lateinit var detailsDialogFragment: WeatherDetailsDialogFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureElements()
        GooglePlaceFragment(fragmentManager = childFragmentManager).getPlace({ cityName, shortName ->
            weatherViewModel.dispatch(WeatherAction.FetchWeatherFromApi(cityName, shortName))
        }) {
            Log.d(TAG, "GooglePlaceError: ${it.statusMessage}")
        }
    }

    override fun onResume() {
        super.onResume()
        weatherViewModel.apply {
            liveState.observe(viewLifecycleOwner, mWeatherFragmentObserver)
            addStatePropertyListener(WeatherViewState::error, this@WeatherFragment::handleViewState)
        }
    }

    override fun onPause() {
        super.onPause()
        weatherViewModel.apply {
            liveState.removeObserver(mWeatherFragmentObserver)
            removeStatePropertyListener(WeatherViewState::error, this@WeatherFragment::handleViewState)
        }
    }


    private fun handleViewState(viewState: WeatherViewState) {
        binding.swipeRefreshLayout.isRefreshing = viewState.showProgressBar
        recycleViewAdapter.submitList(viewState.recycleViewItems)
        viewState.error?.let {
            Toast.makeText(context, it.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }


    private fun configureElements() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            weatherViewModel.dispatch(WeatherAction.UpdateWeathers)
        }
        binding.recyclerView.adapter = recycleViewAdapter
    }


    companion object {
        private val TAG = WeatherFragment::class.java.simpleName

    }
}