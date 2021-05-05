package com.example.simpleweatherapplication.weather.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.databinding.WeatherDetailsFragmentBinding
import cy.com.core.state.actions.WeatherDetailsAction
import cy.com.core.state.viewstates.WeatherDetailsViewState
import com.example.simpleweatherapplication.utils.adapters.WeatherDetailsAdapter
import com.example.simpleweatherapplication.utils.extensions.hasId
import com.example.simpleweatherapplication.utils.fragment.viewBinding
import com.example.simpleweatherapplication.weather.datasource.WeatherActionsItem
import com.example.simpleweatherapplication.weather.viewmodels.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailsDialogFragment(
    private val cityName: String
) : DialogFragment(R.layout.weather_details_fragment) {

    companion object {
        val TAG: String = WeatherDetailsDialogFragment::class.java.simpleName

        fun newInstance(
            cityName: String
        ): WeatherDetailsDialogFragment = WeatherDetailsDialogFragment(cityName)
    }

    private val binding by viewBinding(WeatherDetailsFragmentBinding::bind)

    private val viewModel: WeatherDetailsViewModel by viewModel()

    private val recyclerViewAdapter = WeatherDetailsAdapter().apply {
        listener = { item ->
            when {
                item.hasId(WeatherActionsItem.DONE_BUTTON) -> {
                    dismiss()
                }
            }
        }
    }

    private val mWeatherFragmentObserver = Observer(this::handleViewState)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureRecycleView()
        viewModel.dispatch(WeatherDetailsAction.GetWeatherDetails(cityName))
    }

    override fun onResume() {
        super.onResume()
        viewModel.apply {
            liveState.observe(viewLifecycleOwner, mWeatherFragmentObserver)
            addStatePropertyListener(WeatherDetailsViewState::error, this@WeatherDetailsDialogFragment::handleViewState)
        }
        adjustDialogSize()

    }

    override fun onPause() {
        super.onPause()
        viewModel.apply {
            liveState.removeObserver(mWeatherFragmentObserver)
            removeStatePropertyListener(WeatherDetailsViewState::error, this@WeatherDetailsDialogFragment::handleViewState)
        }
    }

    private fun configureRecycleView() {
        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun handleViewState(viewState: WeatherDetailsViewState) {
        recyclerViewAdapter.submitList(viewState.weatherData)
    }

    private fun adjustDialogSize() {
        dialog?.window.let {
            val displayMetrics = DisplayMetrics()
            it?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val marginTopBottom = resources.getDimensionPixelSize(R.dimen.dialogMarginTopAndBottom) * 2
            val marginSide = resources.getDimensionPixelSize(R.dimen.dialogSideMargin) * 2
            val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
            params.width = displayMetrics.widthPixels - marginSide
            params.height = displayMetrics.heightPixels - marginTopBottom
            it?.attributes = params as WindowManager.LayoutParams
        }
    }

}