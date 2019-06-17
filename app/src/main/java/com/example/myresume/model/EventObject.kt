package com.example.myresume.model

/**
 * EventObject that represents the event event, and identifies caller class
 * callerClass will help identify event callers for better debugging
 */
open class EventObject(val callerClass: String)