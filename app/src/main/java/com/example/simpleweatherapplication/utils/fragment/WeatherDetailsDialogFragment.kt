package com.example.simpleweatherapplication.utils.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.databinding.WeatherDetailsFragmentBinding
import com.example.simpleweatherapplication.utils.RecyclerViewItem
import com.example.simpleweatherapplication.utils.adapters.RecyclerViewAdapter

class WeatherDetailsDialogFragment(
    private val recyclerViewItem: List<RecyclerViewItem>
) : DialogFragment(R.layout.weather_details_fragment) {

    companion object {
        val TAG: String = WeatherDetailsDialogFragment::class.java.simpleName

        fun newInstance(
            recyclerViewItem: List<RecyclerViewItem>
        ): WeatherDetailsDialogFragment = WeatherDetailsDialogFragment(recyclerViewItem)
    }

    private val binding by viewBinding(WeatherDetailsFragmentBinding::bind)

    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureRecycleView()
    }

    private fun configureRecycleView() {
        val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = recyclerViewAdapter
        recyclerViewAdapter.submitList(recyclerViewItem)
    }

    override fun onResume() {
        super.onResume()
        adjustDialogSize()
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