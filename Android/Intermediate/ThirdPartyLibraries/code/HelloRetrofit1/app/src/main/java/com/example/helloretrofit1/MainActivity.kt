package com.example.helloretrofit1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread


val LOG = "android-retrofit"

class MainActivity : AppCompatActivity() {

    // The server code is in Common/Restful/code/HelloRestful1
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val todoApi = retrofit.create(Todo::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadTodoList()
        Thread.sleep(500)
        createTodo()
        Thread.sleep(500)
        readTodo()
        Thread.sleep(500)
        updateTodo()
        Thread.sleep(500)
        deleteTodo()
        Thread.sleep(500)
        downloadTodoList()
    }

    fun downloadTodoList() {
        val call = todoApi.requestTodoList()
        thread {
            val todoList = call.execute().body()
            Log.d(LOG, "Get a list of todo")
            Log.d(LOG, todoList.toString())
        }
    }

    fun createTodo() {
        val call = todoApi.requestCreateTodo("something new")
        thread {
            val todo = call.execute().body()
            Log.d(LOG, "Create a todo")
            Log.d(LOG, todo.toString())
        }
    }

    fun readTodo() {
        val call = todoApi.requestTodo("todo1")

        thread {
            val todo = call.execute().body()
            Log.d(LOG, "Read a todo")
            Log.d(LOG, todo.toString())
        }
    }

    fun updateTodo() {
        val call = todoApi.requestUpdateTodo("todo2", "something updated")

        thread {
            val todo = call.execute().body()
            Log.d(LOG, "Update a todo")
            Log.d(LOG, todo.toString())
        }
    }

    fun deleteTodo() {
        val call = todoApi.requestDeleteTodo("todo2")

        thread {
            val todo = call.execute().body()
            Log.d(LOG, "Delete a todo")
            Log.d(LOG, todo.toString())
        }
    }
}
