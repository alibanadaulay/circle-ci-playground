package com.ali.circle_ci_playground.di.module

import com.ali.circle_ci_playground.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun providesMainActivity(): MainActivity
}