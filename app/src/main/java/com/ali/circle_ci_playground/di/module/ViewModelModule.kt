package com.ali.circle_ci_playground.di.module

import com.ali.circle_ci_playground.ViewModelFactory
import com.ali.circle_ci_playground.data.Dummy
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun providesViewModelFactory(mDummy: Dummy): ViewModelFactory = ViewModelFactory(mDummy)

}