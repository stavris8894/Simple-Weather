package com.example.simpleweatherapplication.state

import android.util.Log
import androidx.lifecycle.*
import com.example.simpleweatherapplication.state.interfaces.Action
import com.example.simpleweatherapplication.state.interfaces.Result
import com.example.simpleweatherapplication.state.interfaces.ViewState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty1

abstract class BaseViewModel<S : ViewState, A : Action, R : Result> : ViewModel() {

    protected abstract fun getInitialState(): S

    protected abstract suspend fun handle(scope: LiveDataScope<R>, action: A)

    protected abstract fun handleError(action: A, error: Throwable): R

    protected abstract fun reduce(result: R): S

    private val mPropertyListenersObserver = Observer<S> {}
    private val mStatePropertyListeners = mutableListOf<Pair<List<KProperty1<S, Any?>>, (state: S) -> Unit>>()

    private fun callStatePropertyListeners(oldState: S, newState: S) {
        mStatePropertyListeners.forEach { (props, listener) ->
            for (prop in props) {
                if (prop.get(newState) != prop.get(oldState)) {
                    listener(newState)
                    break
                }
            }
        }
    }

    private val mDispatchedJobs = ConcurrentHashMap<String, DispatchedJobs>()
    private var mLastDispatchedJobsCleanup = System.currentTimeMillis()
    private val mResult = QueueMutableLiveData<R>()
    private val mResultLiveDataScope: LiveDataScope<R> = BaseLiveDataScope(mResult, viewModelScope.coroutineContext)

    val liveState = Transformations.map(mResult) {
        val oldState = state

        try {
            val newState = reduce(it)

            viewModelScope.launch(Dispatchers.Main) {
                callStatePropertyListeners(oldState, newState)
            }

            newState
        } catch (ex: Throwable) {
            Log.e(
                this@BaseViewModel.javaClass.simpleName,
                "Exception occurred when reduction ${it.javaClass.canonicalName?.substringAfter("results.")}: ${ex.localizedMessage}",
                ex
            )
            oldState
        }
    }

    val state: S
        get() {
            return liveState.value ?: getInitialState()
        }

    fun dispatch(action: A) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            try {
                handle(mResultLiveDataScope, action)
            } catch (ex: CancellationException) {
                //SKIP
            } catch (ex: Throwable) {
                Log.e(
                    this@BaseViewModel.javaClass.simpleName,
                    "Exception occurred when reduction ${action.javaClass.canonicalName?.substringAfter("results.")}: ${ex.localizedMessage}",
                    ex

                )
                mResultLiveDataScope.emit(handleError(action, ex))
            }
            cleanupDispatchesJobs()
        }
        mDispatchedJobs.getOrPut(action.javaClass.name, { DispatchedJobs() }).add(job)
    }

    protected fun cancelPreviousDispatches(action: A, includeLast: Boolean = false) {
        cancelPreviousDispatches(action.javaClass, includeLast)
    }

    protected fun <T : A> cancelPreviousDispatches(action: Class<T>, includeLast: Boolean = false) {
        val dispatchedJobs = mDispatchedJobs[action.name] ?: return
        dispatchedJobs.cancelPreviousJobs(includeLast)
    }

    @Synchronized
    private fun cleanupDispatchesJobs() {
        if (System.currentTimeMillis() - mLastDispatchedJobsCleanup < 30_000) {
            return
        }

        mLastDispatchedJobsCleanup = System.currentTimeMillis()

        try {
            mDispatchedJobs.values.forEach {
                it.clearInactiveJobs()
            }
        } catch (ex: Throwable) {
        }
    }

    fun addStatePropertyListener(prop: KProperty1<S, Any?>, listener: (state: S) -> Unit) {
        addStatePropertyListener(listOf(prop), listener)
    }

    fun addStatePropertyListener(props: List<KProperty1<S, Any?>>, listener: (state: S) -> Unit) {
        mStatePropertyListeners.add(Pair(props, listener))
        if (mStatePropertyListeners.size == 1) {
            liveState.observeForever(mPropertyListenersObserver)
        }
    }

    fun removeStatePropertyListener(prop: KProperty1<S, Any?>, listener: (state: S) -> Unit) {
        removeStatePropertyListener(listOf(prop), listener)
    }

    fun removeStatePropertyListener(props: List<KProperty1<S, Any?>>, listener: (state: S) -> Unit) {
        if (mStatePropertyListeners.size == 1) {
            liveState.removeObserver(mPropertyListenersObserver)
        }
        mStatePropertyListeners.remove(Pair(props, listener))
    }

    fun <J> executeCallback(callback: (J) -> Unit, arg1: J) {
        viewModelScope.launch {
            callback(arg1)
        }
    }

    fun <J, C> executeCallback(callback: (J, C) -> Unit, arg1: J, arg2: C) {
        viewModelScope.launch {
            callback(arg1, arg2)
        }
    }

    fun <J, C, L> executeCallback(callback: (J, C, L) -> Unit, arg1: J, arg2: C, arg3: L) {
        viewModelScope.launch {
            callback(arg1, arg2, arg3)
        }
    }


}