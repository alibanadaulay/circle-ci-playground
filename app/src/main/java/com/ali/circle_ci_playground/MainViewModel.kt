package com.ali.circle_ci_playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ali.circle_ci_playground.`interface`.SchedulerProvides
import com.ali.circle_ci_playground.data.Dummy

class MainViewModel(
    private val mDummy: Dummy
) : ViewModel() {

    val messageFromServer: MutableLiveData<String> = MutableLiveData()


    fun getEmployees() {
        messageFromServer.value = "Loading"
        mDummy.getEmployees()
            .observeOn(SchedulerProvides.main())
            .subscribeOn(SchedulerProvides.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    messageFromServer.value = "Success retrieve data from server"
                } else {
                    messageFromServer.value = "Success retrieve data from server"
                }
            }, {
                messageFromServer.value = "Failed get data from server"
            })
    }
}