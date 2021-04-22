package com.example.simpleweatherapplication.state

import androidx.lifecycle.MutableLiveData
import java.util.*

class QueueMutableLiveData<T> : MutableLiveData<T>() {
    private val mQueuedValues: Queue<T> = LinkedList()
    private var mIsActive: Boolean = false

    override fun onActive() {
        mIsActive = true
        while (true) {
            val value = mQueuedValues.poll() ?: return
            setValue(value)
        }
    }

    override fun onInactive() {
        mIsActive = false
    }

    override fun setValue(value: T) {
        if (mIsActive) {
            super.setValue(value)
        } else
            mQueuedValues.add(value)
    }

    override fun postValue(value: T) {
        if (mIsActive)
            super.postValue(value)
        else
            mQueuedValues.add(value)
    }
}