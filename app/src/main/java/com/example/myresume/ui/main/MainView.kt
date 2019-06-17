package com.example.myresume.ui.main

import com.example.myresume.model.ResumeSection
import com.example.myresume.ui.BaseView

/**
 * MainActivity contract that will be used with MainPresenter
 */
interface MainView : BaseView {
    fun renderResume(resume: Array<ResumeSection>)
    fun setRefreshing(value: Boolean)
}
