package com.example.myresume.eventbus

import com.example.myresume.model.EventObject
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * RxJava Event Bus Class
 */
class RxBus {
    /**
     * Subscription subject
     */
    private val subject = PublishSubject.create<EventObject>()

    /**
     * Getter for this subscribers to listen for events
     * @return All events as observable.
     */
    val events: Observable<EventObject>
        get() = subject

    /**
     * Route event to events subscribers
     * @param eventObject Event object to be posted to eventbus
     */
    fun post(eventObject: EventObject) {
        subject.onNext(eventObject)
    }
}
