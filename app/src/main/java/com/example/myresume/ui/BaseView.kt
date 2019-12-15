package com.example.myresume.ui

/**
 * BaseView contract to be extended by per view contract
 * ex: public interface XyzView extends BaseView
 * Used by Presenter class
 * Contains all basic and common UI methods
 */
interface BaseView {
    /**
     * Changes the toolbar title
     * @param title Title text
     */
    fun setTitle(title: String)

    /**
     * Displays error message
     * @param errorMessage Error message text
     */
    fun showError(errorMessage: String)
}
