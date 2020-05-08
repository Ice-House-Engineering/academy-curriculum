package com.example.helloretrofit1

import retrofit2.Call
import retrofit2.http.*


interface Todo {

    @GET("/todos")
    fun requestTodoList(): Call<Map<String, Map<String, String>>>

    @POST("/todos")
    @FormUrlEncoded
    fun requestCreateTodo(@Field("task") task: String): Call<Map<String, String>>

    @GET("/todos/{todo_id}")
    fun requestTodo(@Path("todo_id") todo_id: String): Call<Map<String, String>>

    @PUT("/todos/{todo_id}")
    @FormUrlEncoded
    fun requestUpdateTodo(@Path("todo_id") todo_id: String, @Field("task") task: String): Call<Map<String, String>>

    @DELETE("/todos/{todo_id}")
    fun requestDeleteTodo(@Path("todo_id") todo_id: String): Call<Unit>
}