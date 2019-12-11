package com.mctech.samplesample_architecture

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MediatorLiveData<T>() {
    private val mPending = AtomicBoolean(false)
    private val mObservers = mutableMapOf<String, Observer<in T>>()

    fun observe(key : String, owner: LifecycleOwner, observer: Observer<in T>) {
        mObservers[key] = observer
        super.observe(owner, Observer<T> { t ->
            synchronized(mObservers){
                if (mPending.compareAndSet(true, false)) {
                    mObservers.forEach {
                        it.value.onChanged(t)
                    }
                }
            }
        })
    }

    fun removeObserver(key : String) {
        synchronized(mObservers){
            mObservers.filter {
                it.key == key
            }.forEach {
                super.removeObserver(it.value)
            }
            mObservers.remove(key)
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}