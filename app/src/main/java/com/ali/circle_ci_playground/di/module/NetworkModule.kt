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
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

@Module
class NetworkModule(private val url: String) {

    companion object {
        private const val TAG = "Network"
        const val TIMEOUT = 5
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
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
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            var response: Response
            val requestBuilder = request.newBuilder()
            try {
                response = chain.proceed(requestBuilder.build())
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
                    Log.e(TAG, response.headers().toString())
                }
            } catch (exception: IOException) {
                response = chain.proceed(request)
            } catch (exception: SocketTimeoutException) {
                response = chain.proceed(request)
            }
            response
        }
    }

    @Provides
    fun createDummy(retrofit: Retrofit) = retrofit.create(Dummy::class.java)
}