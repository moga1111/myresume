package com.example.myresume.eventbus.events.errors

import com.example.myresume.model.EventObject

/**
 * Network Error Event
 */
class NetworkErrorEvent(callerClass: String) : EventObject(callerClass)
