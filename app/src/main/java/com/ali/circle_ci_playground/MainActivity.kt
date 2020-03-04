package com.ali.circle_ci_playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        MainViewModel.mIMain = mIMainViewModelIniliazed()
    }

    private fun mIMainViewModelIniliazed() = object : MainViewModel.IMain {
        override fun onSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onError() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
