package com.laatonwalabhoot.dexter

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

internal class CoroutineLifeCycleObserver(private val deferred: Deferred<*>) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelRoutine() {
        if (!deferred.isCancelled) {
            deferred.cancel()
        }
    }
}

fun <T> LifecycleOwner.load(loader: suspend () -> T): Deferred<T> {
    val deferred = GlobalScope.async(context = Dispatchers.Default, start = CoroutineStart.LAZY) {
        loader()
    }

    lifecycle.addObserver(CoroutineLifeCycleObserver(deferred = deferred))
    return deferred
}

infix fun <T> Deferred<T>.then(block: suspend (T) -> Unit): Job {
    return GlobalScope.launch(context = Dispatchers.Main) {
        try {
            block(this@then.await())
        } catch (e: Exception) {

        }
    }
}