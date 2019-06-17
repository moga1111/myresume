package com.example.myresume.services

import com.example.myresume.model.ResumeSection
import io.reactivex.Observable
import retrofit2.http.GET

interface ResumeService {
    @get:GET("resume.json")
    val resume: Observable<Array<ResumeSection>>
}
