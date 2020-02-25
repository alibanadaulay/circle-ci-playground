package com.ali.circle_ci_playground

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setup() {
        mainPresenter = MainPresenter()
    }

    @Test
    fun verify_return_values_is_true() {
        assert(mainPresenter.sampleTest(2))
    }

    @Test
    fun verify_return_values_is_false() {
        assert(!mainPresenter.sampleTest(7))
    }
}