package com.example.simpleweatherapplication.utils.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.application.App
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.androidx.viewmodel.scope.BundleDefinition
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

val Context.app: App
    get() = applicationContext as App

inline fun <reified T : ViewModel> Context.appViewModel(
    qualifier: Qualifier? = null,
    noinline state: BundleDefinition? = { Bundle.EMPTY },
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        GlobalContext.get().getViewModel(qualifier, state, { ViewModelOwner.from(app, app) }, T::class, parameters)
    }
}

fun FragmentActivity.getCurrentFragment(id: Int = R.id.fragmentContainer): Fragment? {
    return supportFragmentManager.findFragmentById(id)
}

fun FragmentActivity.replaceFragment(fragment: Fragment, id: Int = R.id.fragmentContainer) {
    supportFragmentManager.commit {
        setReorderingAllowed(true)
        replace(id, fragment)
    }
}

fun FragmentActivity.showSelectionFragment(fragment: Fragment, id: Int = R.id.fragmentContainer) {
    supportFragmentManager.commit {
        addToBackStack(null)
        setCustomAnimations(
            R.anim.slide_in_up,
            android.R.anim.fade_out,
            android.R.anim.fade_in,
            R.anim.slide_out_bottom
        )
        replace(id, fragment)
    }
}

fun FragmentActivity.showToast(
    message: String?
) {
    val makeText = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    val view = makeText.view
    view?.background?.colorFilter =
        PorterDuffColorFilter(this.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
    view?.findViewById<TextView>(android.R.id.message)?.setTextColor(this.getColor(R.color.white))
    makeText.show()
}

fun Fragment.showToast(
    message: String?
) {
    val makeText = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
    val view = makeText.view
    view?.background?.colorFilter =
        PorterDuffColorFilter(requireContext().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
    view?.findViewById<TextView>(android.R.id.message)?.setTextColor(requireContext().getColor(R.color.white))
    makeText.show()
}