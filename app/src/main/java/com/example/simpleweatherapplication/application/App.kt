package com.example.simpleweatherapplication.application

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import com.datatheorem.android.trustkit.TrustKit
import com.example.simpleweatherapplication.BuildConfig
import com.example.simpleweatherapplication.di.networkModule
import com.example.simpleweatherapplication.di.repositoryModule
import com.example.simpleweatherapplication.di.viewModelModule
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class App : Application(), ViewModelStoreOwner, SavedStateRegistryOwner, LifecycleOwner {
    private val mViewModelStore = ViewModelStore()
    private val mLifecycleRegistry = LifecycleRegistry(this)
    private val mSavedStateRegistryController = SavedStateRegistryController.create(this)

    private val mProcessLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            mLifecycleRegistry.currentState = Lifecycle.State.CREATED
        }

        override fun onStart(owner: LifecycleOwner) {
            super.onStart(owner)
            mLifecycleRegistry.currentState = Lifecycle.State.STARTED
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            mLifecycleRegistry.currentState = Lifecycle.State.RESUMED
        }

        override fun onStop(owner: LifecycleOwner) {
            super.onStop(owner)
            mLifecycleRegistry.currentState = Lifecycle.State.DESTROYED
        }
    }

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(mProcessLifecycleObserver)
        instance = this
        Places.initialize(applicationContext, BuildConfig.GOOGLE_KEY, Locale.ENGLISH)
        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, viewModelModule, networkModule))
        }
        TrustKit.initializeWithNetworkSecurityConfiguration(this@App)

    }

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }

    override fun getSavedStateRegistry(): SavedStateRegistry {
        return mSavedStateRegistryController.savedStateRegistry
    }

    companion object {
        private lateinit var instance: App

        fun getString(@StringRes resId: Int): String {
            return instance.resources.getString(resId)
        }

        fun getString(@StringRes resId: Int, arg: String): String {
            return instance.resources.getString(resId, arg)
        }
    }
}