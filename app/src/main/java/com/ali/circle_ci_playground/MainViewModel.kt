package com.ali.circle_ci_playground

import androidx.lifecycle.ViewModel
import com.ali.circle_ci_playground.`interface`.SchedulerProvides
import com.ali.circle_ci_playground.data.Dummy
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val mDummy: Dummy) : ViewModel() {

    companion object {
        lateinit var mIMain: IMain
    }

    interface IMain {
        fun onSuccess()
        fun onError()
    }

    private val mCompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun getEmployees() {
        mDummy.getEmployees()
            .observeOn(SchedulerProvides.main())
            .subscribeOn(SchedulerProvides.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    mIMain.onSuccess()
                } else {
                    mIMain.onError()
                }
            }, {
                mIMain.onError()
            })
//        mCompositeDisposable.add(disposable)
    }
}