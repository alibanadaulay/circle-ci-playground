package com.ali.circle_ci_playground.di

import com.ali.circle_ci_playground.App
import com.ali.circle_ci_playground.di.module.NetworkModule
import com.ali.circle_ci_playground.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun builder(): AppComponent

        @BindsInstance
        fun applicattion(app: App): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun viewModuleModule(viewModelModule: ViewModelModule): Builder
    }
}