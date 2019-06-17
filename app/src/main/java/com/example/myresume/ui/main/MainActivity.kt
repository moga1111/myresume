package com.example.myresume.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myresume.MainApplication
import com.example.myresume.R
import com.example.myresume.model.ResumeSection
import com.example.myresume.presenter.main.MainPresenter
import com.example.myresume.presenter.main.MainViewPresenter
import com.example.myresume.ui.BaseActivity
import com.example.myresume.ui.adapters.ResumeAdapter
import javax.inject.Inject


/**
 * Main Activity
 */
class MainActivity : BaseActivity(), MainView {

    //region Fields & Variables
    private var recyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var resumeAdapter: ResumeAdapter? = null

    // Main Presenter
    @set:Inject
    internal var mainPresenter: MainViewPresenter? = null

    /**
     * Layout resource required to reference toolbar
     *
     * @return toolbar id
     */
    override val layoutResource: Int
        get() = R.layout.activity_main
    //endregion

    //region Life Cycle Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainPresenter!!.load()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter!!.resume()
    }

    override fun onPause() {
        mainPresenter!!.pause()
        super.onPause()
    }

    override fun onStop() {
        mainPresenter!!.finish()
        super.onStop()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mainPresenter!!.finish()
    }

    //endregion

    //region Implemented Abstract Methods

    /**
     * Initialize class fields
     */
    override fun init() {
        recyclerView = findViewById(R.id.recycler_view)
        resumeAdapter = ResumeAdapter(arrayOf())
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        swipeRefreshLayout!!.setOnRefreshListener { mainPresenter!!.refreshListRequested() }
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = resumeAdapter
    }

    /**
     * Perform dependency injection
     */
    public override fun inject() {
        (application as MainApplication).component!!.inject(this)
        (application as MainApplication).component!!.inject(mainPresenter as MainPresenter)
    }

    /**
     * Tell the presenter to use this view
     */
    override fun provideViewForPresenter() {
        mainPresenter!!.setView(this)
    }
    //endregion

    //region Contract Methods & UI

    /**
     * Changes the toolbar title
     *
     * @param title Title text
     */
    override fun setTitle(title: String) {
        toolbar!!.title = title
    }

    /**
     * Displays error message
     *
     * @param errorMessage Error message text
     */
    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    /**
     * Hides displayed error message
     */
    override fun hideError() {

    }

    /**
     * Displays progress with message
     *
     * @param progressMessage Progress message text
     */
    override fun showProgress(progressMessage: String?) {

    }

    /**
     * Hide displayed progress
     */
    override fun hideProgress() {

    }


    /**
     * Renders ResumeSection[] object
     * @param resume object
     */
    override fun renderResume(resume: Array<ResumeSection>) {
        if (resumeAdapter != null) {
            resumeAdapter!!.setData(resume)
            resumeAdapter!!.notifyDataSetChanged()
        }
    }

    /**
     * Enable / disable refreshing animation
     * @param value Boolean
     */
    @Synchronized
    override fun setRefreshing(value: Boolean) {
        swipeRefreshLayout!!.isRefreshing = value
    }
    //endregion
}
