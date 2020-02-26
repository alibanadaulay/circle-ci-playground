package com.ali.circle_ci_playground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ali.circle_ci_playground.data.Dummy
import java.lang.IllegalArgumentException

class ViewModelFactory constructor(private val mDummy: Dummy) : ViewModelProvider.Factory {

//    private lateinit var creators:Map<Class<?:Cl>>

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mDummy) as T
        }
        throw IllegalArgumentException("Class not found")
    }
}