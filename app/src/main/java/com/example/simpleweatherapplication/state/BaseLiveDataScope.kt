package com.example.simpleweatherapplication.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class BaseLiveDataScope<T>(private val mLiveData: MutableLiveData<T>, context: CoroutineContext) : LiveDataScope<T> {

    private val mediatorLiveData = MediatorLiveData<T>()

    override val latestValue: T?
        get() = mLiveData.value

    private val coroutineContext = context + Dispatchers.Main.immediate

    private var emittedSource: LiveDataEmittedSource? = null

    override suspend fun emit(value: T) = withContext(coroutineContext) {
        clearSource()
        mLiveData.value = value
    }

    override suspend fun emitSource(source: LiveData<T>): DisposableHandle =
        withContext(coroutineContext) {
            clearSource()
            val newSource = withContext(Dispatchers.Main.immediate) {
                mediatorLiveData.addSource(source) {
                    mLiveData.value = it
                }
                LiveDataEmittedSource(
                    source = source,
                    mediator = mediatorLiveData
                )
            }
            emittedSource = newSource

            return@withContext newSource
        }


    private suspend fun clearSource() {
        emittedSource?.disposeNow()
        emittedSource = null
    }

}