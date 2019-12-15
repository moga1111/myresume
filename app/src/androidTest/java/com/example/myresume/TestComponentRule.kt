package com.example.myresume

import android.content.Context
import com.example.myresume.dagger.module.SharedPreferencesModule
import com.example.myresume.di.component.DaggerTestComponent
import com.example.myresume.di.component.TestComponent
import com.example.myresume.di.module.ApplicationTestModule
import com.example.myresume.di.module.NetworkTestModule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


/**
 * Test rule that would inject our test dependencies
 */
class TestComponentRule(context: Context) : TestRule {
    private lateinit var mTestComponent: TestComponent
    private val mContext: Context = context

    private fun setupDaggerTestComponentInApplication() {
        val application: MainApplication = mContext.applicationContext as MainApplication

        mTestComponent = DaggerTestComponent.builder()
            .applicationTestModule(ApplicationTestModule(application))
            .sharedPreferencesModule(SharedPreferencesModule(mContext))
            .networkTestModule(NetworkTestModule())
            .build()

        application.component = mTestComponent
    }

    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                setupDaggerTestComponentInApplication()
                base.evaluate()
            }
        }
    }
}
