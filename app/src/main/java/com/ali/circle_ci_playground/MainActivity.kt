package com.ali.circle_ci_playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ali.circle_ci_playground.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        initBinding()
        registerObserver()
    }

    private fun initBinding() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainViewModel =
            ViewModelProvider(this, mViewModelFactory).get(MainViewModel::class.java)
        mainBinding.mainViewModel = mainViewModel
    }

    private fun registerObserver() {
        mainViewModel.messageFromServer.observe(this,
            Observer {
                mainBinding.progressRetrieveData = it
            })
    }
}
