package com.example.simpleweatherapplication.utils

import androidx.fragment.app.FragmentManager
import com.example.simpleweatherapplication.R
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class GooglePlaceFragment(private val fragmentManager: FragmentManager) {

    fun getPlace(onPlaceSelected: (cityName: String, countryShortName: String?) -> Unit, onError: (Status) -> Unit) {
        val autocompleteFragment = fragmentManager.findFragmentById(R.id.autoCompleteFragment) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.TYPES, Place.Field.ADDRESS_COMPONENTS))
        autocompleteFragment.setTypeFilter(TypeFilter.CITIES)
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                place.let { it ->
                    val shortName = it.addressComponents?.asList()?.first {
                        it.types.contains("country")
                    }?.shortName
                    onPlaceSelected(it.name ?: "", shortName)
                }
            }

            override fun onError(status: Status) {
                onError(status)
            }
        })

    }

    companion object {
        private val TAG = GooglePlaceFragment::class.java.simpleName
    }
}