package com.example.mvp_mvvm_new.utils
import android.os.Handler
import android.os.Looper


//interface Subscriber<T> {
//    fun post(value: T?)
//}
//private typealias Subscriber<T> = Pair<Handler, (T?) -> Unit>

private class Subscriber<T>(
    private val handler: Handler = Handler(Looper.getMainLooper()),
    private val callBack: (T?) -> Unit
) {
    fun invoke(value: T?) {
        handler.post{
            callBack.invoke(value)
        }
    }
}

/** Проблемы */
// 1. Toast показывается каждый раз при пересоздании Активити

class Publisher<T>(private val isSingle: Boolean = false) {
    private val subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()
    var value: T? = null
        private set
    private var hasFirstValue = false

    fun subscribe(handler: Handler, callBack: (T?) -> Unit) {
        val subscriber = Subscriber(handler, callBack)
        subscribers.add(subscriber)
        if(hasFirstValue) {
            // для подписывания на новые значения
            subscriber.invoke(value)
        }
    }

//    fun unSubscribe(subscriber: Subscriber<T>) {
//        subscribers.remove(subscriber)
//    }

    fun unSubscribeAll() {
        subscribers.clear()
    }

    fun post(value: T) {
        if(!isSingle) {
            hasFirstValue = true
            this.value = value
        }
//        subscribers.forEach { it.invoke(value) }
        subscribers.forEach { it.invoke(value) }
    }
}

/** Лямбда оформления */
//    fun foo(lambda: (Int) -> Double) {
//        lambda(4)
/** или так, без разницы*/
//        lambda.invoke(3)
//    }

