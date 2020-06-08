package com.ali.circle_ci_playground.di.module

import android.util.Log
import com.ali.circle_ci_playground.data.Dummy
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

@Module
class NetworkModule(private val url: String) {

    companion object {
        private const val TAG = "Network"
        const val TIMEOUT = 60
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            var response: Response
            val requestBuilder = request.newBuilder()
            try {
                response = chain.proceed(requestBuilder.build())
                logResponse(response)
            } catch (exception: Exception) {
                response = chain.proceed(request)
            }
            response
        }
    }

    @Provides
    fun createDummy(retrofit: Retrofit) = retrofit.create(Dummy::class.java)

    private fun logResponse(response: Response?) {
        if (response != null) {
            if (response.isSuccessful) {
                Log.i(
                    TAG, "Response: " + response.code() + " <- " +
                            response.networkResponse()!!.request().method() +
                            " " + response.networkResponse()!!.request().url()
                )
            } else {
                Log.e(
                    TAG, "Response: " + response.code() + " <- " +
                            response.networkResponse()!!.request().method() +
                            " " + response.networkResponse()!!.request().url()
                )
            }
        }
    }
}