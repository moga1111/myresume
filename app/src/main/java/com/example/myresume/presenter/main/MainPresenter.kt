package com.example.myresume.presenter.main

import android.util.Log
import com.example.myresume.eventbus.RxBus
import com.example.myresume.eventbus.events.ResumeLoadedEvent
import com.example.myresume.model.ResumeSection
import com.example.myresume.model.interfaces.SharedPreferencesRepository
import com.example.myresume.presenter.EventPresenter
import com.example.myresume.services.ResumeService
import com.example.myresume.ui.main.MainView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

/**
 * MainPresenter is the business logic for MainView.
 */
class MainPresenter
    (rxBus: RxBus) : EventPresenter(rxBus), MainViewPresenter {

    //region Fields & Variables

    // Shared Preferences
    @set:Inject
    internal var sharedPreferences: SharedPreferencesRepository? = null

    @set:Inject
    internal var resumeService: ResumeService? = null

    // Main View
    private var mainView: MainView? = null

    /**
     * Get all resume sections observer
     * @return Observer implementation
     */
    private val resumeObserver: Observer<Array<ResumeSection>>
        get() = object : Observer<Array<ResumeSection>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(resume: Array<ResumeSection>) {
                rxBus.post(ResumeLoadedEvent(resume, TAG))
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message, e)
                mainView!!.showError("Resume load error")
            }

            override fun onComplete() {

            }
        }
    //endregion

    //region Presenter Contract Method

    /**
     * Assign the view
     * @param mainView The activity that implements MainView
     */
    override fun setView(mainView: MainView) {
        this.mainView = mainView
    }

    /**
     * Called when onCreate is done
     */
    override fun load() {
        subscribe()
    }

    /**
     * Called when onResume is done
     */
    override fun resume() {
        mainView!!.setRefreshing(true)
        fetchResume()
    }

    /**
     * Called when onPause() is being called
     */
    override fun pause() {

    }

    /**
     * Called when onStop() is being called
     */
    override fun finish() {
        unsubscribe()
    }

    /**
     * Called when user requests resume update
     */
    override fun refreshListRequested() {
        mainView!!.setRefreshing(true)
        fetchResume()
    }

    override fun onResumeLoadedEvent(resume: Array<ResumeSection>) {
        mainView!!.renderResume(resume)
        mainView!!.setRefreshing(false)
    }

    /**
     * Calls the gist api to get the resume sections, and sends an event with the results
     */
    private fun fetchResume() {
        resumeService!!.resume
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(resumeObserver)
    }

    companion object {
        // Tag
        private val TAG = MainPresenter::class.java.name
    }
    //endregion
}
