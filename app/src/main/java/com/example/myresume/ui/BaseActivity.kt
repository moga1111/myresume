package com.example.myresume.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.myresume.R

/**
 * BaseActivity is to be extended by all activities to abstract common tasks/features.
 */
abstract class BaseActivity : AppCompatActivity() {

    // Common toolbar
    protected lateinit var toolbar: Toolbar

    /**
     * Layout resource required to reference toolbar
     * @return toolbar id
     */
    protected abstract val layoutResource: Int

    //region Life Cycle Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)

        initViews()
        inject()
        init()
        provideViewForPresenter()
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }
    //endregion

    //region Menu methods
    /**
     * Inflate menu
     * @param menu Resource menu
     * @return true to display the menu
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /**
     * Process the selected item
     * @param item Menu item that will be processed
     * @return true if item selected
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.results_menu_item -> {
                Toast.makeText(
                    this,
                    R.string.not_implemented_yet,
                    Toast.LENGTH_SHORT
                ).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    //endregion

    //region To be implemented by child activities
    /**
     * Initialize class fields
     */
    protected abstract fun init()

    /**
     * Perform dependency injection
     */
    protected abstract fun inject()

    /**
     * Set presenter view
     */
    protected abstract fun provideViewForPresenter()
    //endregion
}
