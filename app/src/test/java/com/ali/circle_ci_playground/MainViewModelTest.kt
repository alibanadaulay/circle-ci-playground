package com.ali.circle_ci_playground

import com.ali.circle_ci_playground.data.Dummy
import com.ali.circle_ci_playground.data.model.Employee
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.net.HttpURLConnection


class MainViewModelTest {

    private val mMockWebServer = MockWebServer()
    @Mock
    lateinit var mDummy: Dummy
    private lateinit var serverDUmmy: Dummy

    private lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var main: MainViewModel.IMain

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        MainViewModel.mIMain = main
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        mMockWebServer.start(8080)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        serverDUmmy = retrofit.create(Dummy::class.java)
        mainViewModel = MainViewModel(mDummy)
    }

    @After
    fun tearDown() {
        mMockWebServer.shutdown()
    }

    @Test
    fun getEmployeesSuccess() {
        val result = getJson("json/employees.json")
        val response = MockResponse().setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(result)
        mMockWebServer.enqueue(response)
        val final: TestObserver<Response<List<Employee>>> = serverDUmmy.getEmployees().test()
        final.assertNoErrors()
        final.assertComplete()
//        final.dispose()
//        assert(final.isDisposed)
    }

    @Test
    fun verify_on_success_is_called() {
        val mockList: ArrayList<Employee> = ArrayList()
        mockList.add(Employee(1, "a", 20000.0, 22))
        val list: List<Employee> = mockList
        Mockito.`when`(mDummy.getEmployees()).thenReturn(Observable.just(Response.success(list)))
        mainViewModel.getEmployees()
        Mockito.verify(main, times(1)).onSuccess()
    }

    @Test
    fun verify_on_onError_is_called() {
        Mockito.`when`(mDummy.getEmployees()).thenReturn(Observable.error(Throwable()))
        mainViewModel.getEmployees()
        Mockito.verify(main, times(1)).onError()
    }

    fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri!!.path)
        return String(file.readBytes())
    }
}