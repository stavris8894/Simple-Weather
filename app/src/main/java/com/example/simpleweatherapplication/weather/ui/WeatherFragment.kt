package com.example.simpleweatherapplication.weather.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.databinding.MainPageFragmentBinding
import com.example.simpleweatherapplication.utils.EventObserver
import com.example.simpleweatherapplication.utils.adapters.RecyclerViewAdapter
import com.example.simpleweatherapplication.utils.extensions.showToast
import com.example.simpleweatherapplication.utils.fragment.WeatherDetailsDialogFragment
import com.example.simpleweatherapplication.utils.fragment.viewBinding
import com.example.simpleweatherapplication.weather.viewmodels.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WeatherFragment : Fragment(R.layout.main_page_fragment) {

    private val binding by viewBinding(MainPageFragmentBinding::bind)

    private lateinit var recycleViewAdapter: RecyclerViewAdapter

    private val weatherViewModel: WeatherViewModel by sharedViewModel()

    private lateinit var detailsDialogFragment: WeatherDetailsDialogFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureObservers()
        configureElements()
    }

    private fun configureElements() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            weatherViewModel.refreshData()
        }
    }

    private fun configureObservers() {
        weatherViewModel.recycleViewItems.observe(viewLifecycleOwner, EventObserver {
            recycleViewAdapter = RecyclerViewAdapter(it)
            binding.recyclerView.adapter = recycleViewAdapter
        })

        weatherViewModel.showProgressBar.observe(viewLifecycleOwner, EventObserver {
            binding.swipeRefreshLayout.isRefreshing = it
        })

        weatherViewModel.showErrorMessage.observe(viewLifecycleOwner, EventObserver {
            showToast(it)
        })

        weatherViewModel.showCityDetails.observe(viewLifecycleOwner, EventObserver {
            detailsDialogFragment = WeatherDetailsDialogFragment.newInstance(it)
            detailsDialogFragment.show(childFragmentManager, WeatherDetailsDialogFragment.TAG)
        })

        weatherViewModel.dismissCityDetails.observe(viewLifecycleOwner, EventObserver {
            detailsDialogFragment.dismiss()
        })
    }
}