package com.ali.circle_ci_playground.`interface`

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvides {
    companion object {
        fun main() = AndroidSchedulers.mainThread()
        fun io() = Schedulers.io()
//        fun computing() = Schedulers.computation()
    }
}