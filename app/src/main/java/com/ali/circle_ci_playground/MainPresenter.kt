package com.ali.circle_ci_playground

class MainPresenter(private val view: MainTest) {

    interface MainTest {
        fun onSuccess()
        fun onError()
    }

    fun sampleTest(value: Int): Boolean {
        return value.rem(2) == 0
    }

    fun getData(string: String?) {
        if (string == null || string.isEmpty()) {
            view.onError()
        } else {
            view.onSuccess()
        }
    }
}