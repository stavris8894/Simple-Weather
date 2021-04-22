package com.example.simpleweatherapplication.state

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.*

class LiveDataEmittedSource(
    private val source: LiveData<*>,
    private val mediator: MediatorLiveData<*>
) : DisposableHandle {
    private var disposed = false

    suspend fun disposeNow() = withContext(Dispatchers.Main.immediate) {
        removeSource()
    }

    @MainThread
    private fun removeSource() {
        if (!disposed) {
            mediator.removeSource(source)
            disposed = true
        }
    }

    override fun dispose() {
        CoroutineScope(Dispatchers.Main.immediate).launch {
            removeSource()
        }
    }
}