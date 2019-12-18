package com.example.myresume.helpers

import com.example.myresume.TestMainPresenter
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse

import okhttp3.mockwebserver.RecordedRequest


internal class MockServerDispatcher {
    /**
     * Return ok response from mock server
     */
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            when (request.path) {
                "/resume.json" -> return MockResponse()
                    .setResponseCode(200)
                    .setBody(TestMainPresenter.responseBody)
            }

            return MockResponse().setResponseCode(404)
        }
    }

    /**
     * Return error response from mock server
     */
    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(404)
        }
    }
}
