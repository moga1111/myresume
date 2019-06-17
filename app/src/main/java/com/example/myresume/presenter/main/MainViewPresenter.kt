package com.example.myresume.presenter.main

import com.example.myresume.presenter.Presenter
import com.example.myresume.ui.main.MainView

/**
 * MainView Presenter Contract
 */
interface MainViewPresenter : Presenter<MainView> {
    fun refreshListRequested()
}
