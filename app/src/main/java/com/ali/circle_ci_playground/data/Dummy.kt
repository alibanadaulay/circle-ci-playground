package com.ali.circle_ci_playground.data

import com.ali.circle_ci_playground.data.model.Employee
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface Dummy {
    @GET("employees")
    fun getEmployees(): Observable<Response<List<Employee>>>
}