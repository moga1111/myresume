package com.example.myresume.presenter

import com.example.myresume.eventbus.RxBus
import com.example.myresume.eventbus.events.ResumeLoadedEvent
import com.example.myresume.eventbus.events.errors.NetworkErrorEvent
import com.example.myresume.model.EventObject
import com.example.myresume.model.ResumeSection
import com.example.myresume.util.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * EventPresenter class that must be extended by all presenters.
 * All child classes are subscribed automatically to the event bus, can start listening by
 * overriding event methods in this class
 */
abstract class EventPresenter

protected constructor(// RxBus instance that is set by child classes via super()
    val rxBus: RxBus
) {

    private var eventsSubscription: Disposable? = null

    init {
        subscribe()
    }

    //region Private Methods & Classes
    /**
     * Subscribe to the event bus
     */
    protected fun subscribe() {
        eventsSubscription = rxBus
            .events
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(CustomConsumer())
    }

    /**
     * Unsubscribe from the event bus
     */
    protected fun unsubscribe() {
        if (eventsSubscription != null && !eventsSubscription!!.isDisposed) {
            eventsSubscription!!.dispose()
        }
    }

    /**
     * Consumer implementation, where event objects are processed
     */
    private inner class CustomConsumer : Consumer<EventObject> {

        // Start checking for events types
        @Throws(Exception::class)
        override fun accept(o: EventObject) {

            if (o is NetworkErrorEvent) {
                onNetworkError()
                Util.log(
                    Util.LoggerLevel.INFO,
                    TAG,
                    "NetworkErrorEvent called by " + o.callerClass
                )
            }
            if (o is ResumeLoadedEvent) {
                onResumeLoadedEvent(o.resume)
                Util.log(
                    Util.LoggerLevel.INFO,
                    TAG,
                    "ResumeLoadedEvent called by " + o.callerClass
                )
            }
        }
    }
    //endregion

    //region Optional Event Methods For Child Classes To Override
    /**
     * Invoked when there is a network error
     */
    protected fun onNetworkError() {}

    protected open fun onResumeLoadedEvent(resume: Array<ResumeSection>) {}

    companion object {
        // EventPresenter Tag
        private val TAG = EventPresenter::class.java.name
    }
    //endregion
}
