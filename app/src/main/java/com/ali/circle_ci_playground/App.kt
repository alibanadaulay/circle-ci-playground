package com.ali.circle_ci_playground

import android.app.Activity
import android.app.Application
import com.ali.circle_ci_playground.di.AppComponent
import com.ali.circle_ci_playground.di.DaggerAppComponent
import com.ali.circle_ci_playground.di.module.NetworkModule
import com.ali.circle_ci_playground.di.module.ViewModelModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    companion object {
        private lateinit var mAppComponent: AppComponent
    }

    @Inject
    lateinit var activityDispatchinInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchinInjector

    override fun onCreate() {
        super.onCreate()
        createAppComponent()

    }

    private fun createAppComponent() {
        mAppComponent = DaggerAppComponent.builder()
            .applicattion(this)
            .networkModule(NetworkModule("http://dummy.restapiexample.com/api/v1/"))
            .viewModuleModule(ViewModelModule())
            .builder()
        mAppComponent.inject(this)


    }
}