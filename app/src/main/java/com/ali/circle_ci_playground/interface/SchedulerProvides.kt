package com.ali.circle_ci_playground.`interface`

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.schedulers.Schedulers

class SchedulerProvides {
    companion object {
        fun main() = AndroidSchedulers.mainThread()
        fun io() = Schedulers.io()
        fun computing() = Schedulers.computation()
    }
}