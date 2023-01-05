package com.template.mviexample.util

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 *
 * @author: est7
 * @date: 2022/12/14
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val isPending: AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(
                TAG,
                "Multiple observers registered but only one will be notified of changes."
            )
        }
        // Observe the internal MutableLiveData
        super.observe(owner, interceptObserver(observer))
    }

    override fun observeForever(observer: Observer<in T>) {
        super.observeForever(interceptObserver(observer))
    }

    @MainThread
    override fun setValue(@Nullable t: T?) {
        isPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    private fun interceptObserver(observer: Observer<in T>) = Observer<T> { value ->
        if (isPending.compareAndSet(true, false)) {
            observer.onChanged(value)
        }
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}
