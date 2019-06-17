package com.example.myresume.eventbus.events

import com.example.myresume.model.EventObject
import com.example.myresume.model.ResumeSection

class ResumeLoadedEvent(val resume: Array<ResumeSection>, callerClass: String) : EventObject(callerClass)
