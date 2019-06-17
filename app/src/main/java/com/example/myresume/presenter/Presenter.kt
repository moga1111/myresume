package com.example.myresume.presenter


import com.example.myresume.ui.BaseView

/**
 * Presenter contract to be extended by per-view presenter
 * ex: public interface XyzViewPresenter implements Presenter<XyzView> {
 *
 * For each activity (UI) that is created, the business logic must be separated.
 * Create presenter class, make that presenter implement the child that extends this interface,
 * then use the newly created presenter within the activity. The activity must be dump,
 * and should only handle UI stuff  by obeying the presenter commands.
</XyzView> */
interface Presenter<V : BaseView> {

    /**
     * Assign the view to the presenter, this method must be called in order for the presenter
     * to recognize the view.
     * @param v Activity that extends V that extends BaseView
     */
    fun setView(v: V)

    /**
     * Load function, typically should be called as the last line of
     * onCreate() function within the view
     */
    fun load()

    /**
     * Resume function, typically should be called as the last line of
     * onResume() function within the view
     */
    fun resume()

    /**
     * Pause function, typically should be called as the onPause() function
     */
    fun pause()

    /**
     * finish function, typically should be called as the onStop() function
     */
    fun finish()
}
