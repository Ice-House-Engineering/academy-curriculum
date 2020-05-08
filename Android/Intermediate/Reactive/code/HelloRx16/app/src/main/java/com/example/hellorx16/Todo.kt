package com.example.hellorx16

import io.reactivex.Observable
import retrofit2.http.GET


interface Todo {

    @GET("/todos")
    fun requestTodoList(): Observable<Map<String, Map<String, String>>>
}