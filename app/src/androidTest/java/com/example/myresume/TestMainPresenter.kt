package com.example.myresume

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.myresume.helpers.MockServerDispatcher
import com.example.myresume.helpers.MyViewAction.descAtPosition
import com.example.myresume.helpers.MyViewAction.headerAtPosition
import com.example.myresume.presenter.main.MainViewPresenter
import com.example.myresume.ui.main.MainActivity
import com.example.myresume.ui.main.MainView
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


@RunWith(AndroidJUnit4ClassRunner::class)
class TestMainPresenter {

    private lateinit var webServer: MockWebServer

    private val component: TestComponentRule =
        TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val main: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    var chain: TestRule = RuleChain.outerRule(component).around(main)

    @Mock
    lateinit var mainView : MainView

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        webServer = MockWebServer()
        webServer.setDispatcher(MockServerDispatcher().RequestDispatcher())
        webServer.start(5678)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
    }

    /**
     * Launch activity, should verify some of the mocked data are shown in the list
     */
    @Test
    fun testDataRender () {
        main.launchActivity(Intent())

        // Test that header "Section 1" is showing
        onView(withId(R.id.recycler_view))
            .check(matches(headerAtPosition(0, withText("Section 1"))))

        // Test that header "Professional Summary" is showing
        onView(withId(R.id.recycler_view))
            .check(matches(headerAtPosition(1, withText("Professional Summary"))))

        // Test that description of "Professional Summary" is showing
        onView(withId(R.id.recycler_view))
            .check(
                matches(
                    descAtPosition(
                        1, withText("Java and Android Developer. 5 Years.\n")
                    )
                )
            )

    }

    /**
     * Launch activity, presenter requests the list, and main view should be refreshing as a result.
     */
    @Test
    fun testDataRefresh() {
        main.launchActivity(Intent())

        val presenter: MainViewPresenter = main.activity.mainPresenter
        presenter.setView(mainView)

        presenter.refreshListRequested()
        Mockito.verify(mainView).setRefreshing(true)
    }

    /**
     * Launch activity with error dispatcher, calls the network with a failure.
     */
    @Test
    fun testNetworkError() {
        webServer.setDispatcher(MockServerDispatcher().ErrorDispatcher())
        main.launchActivity(Intent())

        val presenter: MainViewPresenter = main.activity.mainPresenter
        presenter.setView(mainView)

        onView(withId(R.id.swipe_refresh_layout)).perform(swipeDown())
        Mockito.verify(mainView, times(2)).showError("Resume load error")
    }

    companion object Constants {
        val responseBody: String = """
                                [
                                  {
                                    "header": "Section 1",
                                    "items": [
                                      "Name: John Smith",
                                      "Address: 123 Fun st",
                                      "Phone: +1-888-999-999",
                                      "Email: example@gmail.com"
                                    ]
                                  },
                                  {
                                    "header": "Professional Summary",
                                    "items": [
                                      "Java and Android Developer. 5 Years."
                                    ]
                                  },
                                  {
                                    "header": "Key Skills",
                                    "items": [
                                      "Android: Java, Kotlin, Android SDK, REST, XML, JSON, Google Services",
                                      "Back-end: Php, Databases, Laravel, REST API, Apache Server, Play Framework.",
                                      "Web: Javascript, JQuery, CSS.",
                                      "CI, Automation: Ansible, Jenkins, CircleCI, fastlane."
                                    ]
                                  },
                                  {
                                    "header": "Certifications",
                                    "items": [
                                      "Red Hat Certified Engineer (RHCE) May 2008 to Present Linux+ February 2008 to Present"
                                    ]
                                  },
                                  {
                                    "header": "Experience",
                                    "items": [
                                      "Senior Full Stack Developer , 6/2018 to Present",
                                      "Vericatch - Vancouver, BC",
                                      "Responsible for developing, maintaining, 2 Android apps.",
                                      "Re-factor, restructure, and redesign current and future apps.",
                                      "Involved in the designing process of the back-end, and contribute when needed.",
                                      "Configure and maintain cloud, ci, and all related services."
                                    ]
                                  },
                                  {
                                    "header": "Education",
                                    "items": [
                                      "Bachelor of Science , Computer Science, 2014",
                                      "Thompson Rivers University - Kamloops, BC",
                                      "3.21/4 GPA",
                                      "Coursework in Web Design and Development",
                                      "Computational Science and Engineering coursework",
                                      "3 Coursework in Android Development for Mobile and Wear"
                                    ]
                                  }
                                ]
                            """.trimIndent()
    }
}