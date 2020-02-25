package com.ali.circle_ci_playground

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private lateinit var mView: MainPresenter.MainTest
    private lateinit var mainPresenter: MainPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mainPresenter = MainPresenter(mView)
    }

    @Test
    fun verify_return_values_is_true() {
        assert(mainPresenter.sampleTest(2))
    }

    @Test
    fun verify_return_values_is_false() {
        assert(!mainPresenter.sampleTest(7))
    }

    @Test
    fun verify_return_is_onError() {
        mainPresenter.getData(null)
        Mockito.verify(mView, times(1)).onError()
    }

    @Test
    fun verify_return_is_onSuccess(){
        mainPresenter.getData("sample")
        Mockito.verify(mView, times(1)).onError()
    }
}