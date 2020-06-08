package com.ali.circle_ci_playground

import com.ali.circle_ci_playground.data.Dummy
import com.ali.circle_ci_playground.data.model.Employee
import com.ali.circle_ci_playground.di.module.NetworkModule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.SocketPolicy
import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

//@RunWith(MockitoJUnitRunner.class)
class MainViewModelTest {

    private val mMockWebServer = MockWebServer()

    @Mock
    lateinit var mDummy: Dummy
    private lateinit var serverDummy: Dummy

    private lateinit var mainViewModel: MainViewModel
    private val TIMEOUT = 5

    companion object {
        @BeforeClass
        @JvmStatic
        fun setUp() {
            val immediate = object : Scheduler() {
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(
                        Executor { obj: Runnable -> obj.run() },
                        false
                    )
                }
            }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        }
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mMockWebServer.start(8080)
        val retrofit = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .build()
            )
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        serverDummy = retrofit.create(Dummy::class.java)
        mainViewModel = MainViewModel(mDummy)
    }

    @After
    fun tearDown() {
        mMockWebServer.shutdown()
        reset(mDummy)
    }

    @Test
    fun getEmployeesSuccess() {
        val result = getJson("json/employees.json")
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(result)
        mMockWebServer.enqueue(response)
        val final: TestObserver<Response<List<Employee>>> = serverDummy.getEmployees().test()
        final.assertNoErrors()
        final.assertComplete()
    }

    @Test
    fun get_employee_timeout() {
        val response = MockResponse()
            .setBody("[]")
        response.socketPolicy = SocketPolicy.NO_RESPONSE
        mMockWebServer.enqueue(response)
        val final: TestObserver<Response<List<Employee>>> = serverDummy.getEmployees().test()
        final.assertError(SocketTimeoutException::class.java)
        final.assertNotComplete()
        final.assertNoValues()
    }

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri!!.path)
        return String(file.readBytes())
    }
}