package com.example.simpleweatherapplication.utils.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.androidx.viewmodel.scope.BundleDefinition
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

inline fun <reified T : ViewModel> Fragment.appViewModel(
    qualifier: Qualifier? = null,
    noinline state: BundleDefinition? = { Bundle.EMPTY },
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        GlobalContext.get().getViewModel(qualifier, state, { ViewModelOwner.from(requireContext().app, requireContext().app) }, T::class, parameters)
    }
}

inline fun <reified T : ViewModel> Fragment.navGraphViewModel(
    @IdRes navGraphId: Int,
    qualifier: Qualifier? = null,
    noinline state: BundleDefinition? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        val navBackStackEntry = findNavController().getBackStackEntry(navGraphId)
        require(navBackStackEntry.destination is NavGraph) {
            ("No NavGraph with ID $navGraphId is on the NavController's back stack")
        }
        GlobalContext.get().getViewModel(qualifier, state, { ViewModelOwner.from(navBackStackEntry, navBackStackEntry) }, T::class, parameters)
    }
}