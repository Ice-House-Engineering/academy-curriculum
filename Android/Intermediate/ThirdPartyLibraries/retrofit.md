# Retrofit

Retrofit is a library for networking with the server. It is more high level than Okhttp library.

## Clear Text Configuration

Because we use local server to test the networking code. We need to create a custom networking configuration. By default, Android project can only access https not http.

Create a network security configuration file, app / res / xml / network_security_config.xml.
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">10.0.2.2</domain>
    </domain-config>
</network-security-config>
```

By default our localhost has ip address 10.0.2.2, so in this case, we permit clear text communication with the server which has ip address 10.0.2.2.

Then edit manifest file: AndroidManifest.xml. Add these two permissions outside “application” node.
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

Add this attribute on “application” node.
```xml
android:networkSecurityConfig="@xml/network_security_config"
```

## Installing Library

Edit build.gradle (Module: app).
```gradle
implementation "com.squareup.retrofit2:retrofit:2.6.3"
implementation "com.squareup.retrofit2:converter-gson:2.6.3"
implementation "com.squareup.retrofit2:converter-scalars:2.6.3"
```

The gson library is needed if we want to parse JSON string. The scalars library is needed if we want to get raw text. We don’t have to use them both.

Sync it.

## Interface (@GET, @POST, @PUT, @DELETE)

Create a new empty Activity application. Name it HelloRetrofit1. The server code is Common/Restful/code/HelloRestful1.

Create an interface: app / java / com.example.helloretrofit1 / Todo.
```kotlin
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
```

Inside this interface, we create an interaction for each API end point.

We start with GET request of todo list.
```kotlin
    @GET("/todos")
    fun requestTodoList(): Call<Map<String, Map<String, String>>>
```

As we can see, we annotate the method with @GET with parameter “/todos”. The parameter is the API end point. The return type of this method is `Call<T>. T is the type of the data we got. In this case, we get a json string like: {"todo1": {"task": "build an API"}, "todo3": {"task": "profit!"}, "todo2": {"task": "?????"}}`

The T is `Map<String, Map<String, String>>`. As we can see, we have a map of string to map of string to string. The json string will be parsed automatically into the object with this data type.

To create a todo, we use POST request.
```kotlin
@POST("/todos")
@FormUrlEncoded
fun requestCreateTodo(@Field("task") task: String): Call<Map<String, String>>
```

We annotate the post method with @POST with parameter “/todos”. There is additional @FormUrlEncoded. This is needed because we send data in this POST request. This data can be set with the parameter for this method (requestCreateTodo): @Field(“task”) task: String. Remember if we send data to this endpoint with curl, the command would look like this: curl http://localhost:5000/todos -d "task=something new" -X POST

The return type of this method is a map of string to string.

The GET request for specific todo is similar to the GET request of todo list.
```kotlin
    @GET("/todos/{todo_id}")
    fun requestTodo(@Path("todo_id") todo_id: String): Call<Map<String, String>>
```

But the API end point accepts parameter. The API end point can be “/todos/todo1” or “/todos/todo2”.

The parameter for API end point is sent in the request method (requestTodo): @Path(“todo_id”) todo_id: String. Notice the annotation for the parameter is @Path not @Field. @Path is for API, @Field is for request body.

The put request combines both parameters.
```kotlin
    @PUT("/todos/{todo_id}")
    @FormUrlEncoded
    fun requestUpdateTodo(@Path("todo_id") todo_id: String, @Field("task") task: String): Call<Map<String, String>>
```

The delete request uses @DELETE annotation.
```kotlin
    @DELETE("/todos/{todo_id}")
    fun requestDeleteTodo(@Path("todo_id") todo_id: String): Call<Unit>
```

The return type of delete method is nothing so Unit is used.

Edit app / java / com.example.helloretrofit1 / MainActivity.

Add these import lines.
```kotlin
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread
```

Create a constant variable for logging.
```kotlin
val LOG = "android-retrofit"
```

Create a Retrofit object inside MainActivity class.
```kotlin
// The server code is in Common/Restful/code/HelloRestful1
private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:5000")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

We use builder pattern. We use “baseUrl” method to set the host. Then we use “addConverterFactory” method to set the factory we use to convert json string to object. We execute “build” method to get the object.

Then we create an object from our interface with Retrofit object.
```kotlin
private val todoApi = retrofit.create(Todo::class.java)
```

Create a new method to get todo list. Name it downloadTodoList.
```kotlin
fun downloadTodoList() {
    val call = todoApi.requestTodoList()
    thread {
        val todoList = call.execute().body()
        Log.d(LOG, "Get a list of todo")
        Log.d(LOG, todoList.toString())
    }
}
```

We call the method from the interface, which is requestTodoList.

To get the body result of the request, we use “execute().body()”. We have to do this not in the main thread.

Create a new method to create a todo. Name it createTodo.
```kotlin
fun createTodo() {
    val call = todoApi.requestCreateTodo("something new")
    thread {
        val todo = call.execute().body()
        Log.d(LOG, "Create a todo")
        Log.d(LOG, todo.toString())
    }
}
```

Create a new method to read a single todo. Name it readTodo.
```kotlin
fun readTodo() {
    val call = todoApi.requestTodo("todo1")

    thread {
        val todo = call.execute().body()
        Log.d(LOG, "Read a todo")
        Log.d(LOG, todo.toString())
    }
}
```

As we can see, we send a parameter to requestTodo method.

Create a new method to update a single todo. Name it updateTodo.
```kotlin
fun updateTodo() {
    val call = todoApi.requestUpdateTodo("todo2", "something updated")

    thread {
        val todo = call.execute().body()
        Log.d(LOG, "Update a todo")
        Log.d(LOG, todo.toString())
    }
}
```

Create a new method to delete a single todo. Name it deleteTodo.
```kotlin
fun deleteTodo() {
    val call = todoApi.requestDeleteTodo("todo2")

    thread {
        val todo = call.execute().body()
        Log.d(LOG, "Delete a todo")
        Log.d(LOG, todo.toString())
    }
}
```

Execute all of these methods inside onCreate method.
```kotlin
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
```

If we ran the application, we would get this output.
```
2019-12-26 12:12:06.015 8453-8555/? D/android-retrofit: Get a list of todo
2019-12-26 12:12:06.016 8453-8555/? D/android-retrofit: {todo1={task=build an API}, todo2={task=????}, todo3={task=profit}}
2019-12-26 12:12:06.500 8453-8564/? D/android-retrofit: Create a todo
2019-12-26 12:12:06.500 8453-8564/? D/android-retrofit: {task=something new}
2019-12-26 12:12:07.001 8453-8579/com.example.helloretrofit1 D/android-retrofit: Read a todo
2019-12-26 12:12:07.001 8453-8579/com.example.helloretrofit1 D/android-retrofit: {task=build an API}
2019-12-26 12:12:07.504 8453-8636/com.example.helloretrofit1 D/android-retrofit: Update a todo
2019-12-26 12:12:07.504 8453-8636/com.example.helloretrofit1 D/android-retrofit: {task=something updated}
2019-12-26 12:12:08.010 8453-8637/com.example.helloretrofit1 D/android-retrofit: Delete a todo
2019-12-26 12:12:08.010 8453-8637/com.example.helloretrofit1 D/android-retrofit: null
2019-12-26 12:12:08.505 8453-8639/com.example.helloretrofit1 D/android-retrofit: Get a list of todo
2019-12-26 12:12:08.505 8453-8639/com.example.helloretrofit1 D/android-retrofit: {todo1={task=build an API}, todo3={task=profit}, todo4={task=something new}}
```

## Query Parameters

Sometimes we want to interact with the server that accepts query parameters. It is like http://localhost:5000/download?file=song.mp3. “file” is a query parameter. Its value is “song.mp3”.

We also sometimes deal with text response, not JSON string response.

Create a new empty Activity project. Name it HelloRetrofit2. The server code is in Common/Backend/code/HelloBackend10.

Create an interface for Retrofit, app / java / com.example.helloretrofit2 / QueryParam.
```kotlin
package com.example.helloretrofit2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface QueryParam {

    @GET("/")
    fun requestUpperName(@Query("name") lowerName: String): Call<String>
}
```

We use GET request to “/” API end point. We also accepts parameter for query: @Query(“name”) lowerName: String.  The response has String data type: `Call<String>`.

Edit app / java / com.example.helloretrofit2 / MainActivity.

Add these import lines.
```kotlin
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread
```

Create a constant variable.
```kotlin
const val LOG = "android-retrofit"
```

Create a Retrofit variable.
```kotlin
// The server code is in Common/Restful/code/HelloBackend10
private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:5000")
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()
```

This time, we use ScalarsConverterFactory not GsonConverterFactory because we deal with raw text not json string.

Create an object from the Retrofit interface.
```kotlin
private val upperStringApi = retrofit.create(QueryParam::class.java)
```

Create a method to send a query parameter to the server.
```kotlin
fun queryParameter() {
    val call = upperStringApi.requestUpperName("james")
    thread {
        val upperString = call.execute().body()
        Log.d(LOG, upperString)
    }
}
```

It is similar to previous examples. We call requestUpperName method with a query value. Then we get the body of the response from “execute().body().

Call this method inside onCreate method.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    queryParameter()
}
```

If we ran the application, we would get this output.
```
2019-12-27 13:44:35.789 10136-10172/? D/android-retrofit: JAMES
```

## Header

Sometimes we have to modify a header before we send the request to the server. For example: we want to authenticate against the server with token.

Create a new empty Activity application. Name it HelloRetrofit3. The server code is in Common/Backend/code/HelloBackend9.

Create a Retrofit interface: app / java / com.example.helloretrofit3 / JwtAuthentication.
```kotlin
package com.example.helloretrofit3

import retrofit2.Call
import retrofit2.http.*


data class LoginRequest(val username: String, val password: String)

interface JwtAuthentication {

    @POST("/auth")
    @Headers("Content-Type: application/json")
    fun getToken(@Body login: LoginRequest): Call<Map<String, String>>

    @GET("/secret")
    fun getSecret(@Header("Authorization") token: String): Call<String>
}
```

The LoginRequest data class is the way we wrap our input into JSON format.
```kotlin
data class LoginRequest(val username: String, val password: String)
```

We want to send json string as the request’s body. It looks like this:
```json
{"username":"john", "password":"password"}
```

We get the token from “/auth” end point. The result is a map of string to string. The parameter is body request type: @Body login: LoginRequest. The LoginRequest object’s will be serialized into json string.
```kotlin
    @POST("/auth")
    @Headers("Content-Type: application/json")
    fun getToken(@Body login: LoginRequest): Call<Map<String, String>>
```

If we want to set the header dynamically, we can use Header type parameter: @Header(“Authorization”) token: String.
```kotlin
    @GET("/secret")
    fun getSecret(@Header("Authorization") token: String): Call<String>
```

Edit app / java / com.example.helloretrofit3 / MainActivity.

Add these import lines.
```kotlin
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread
```

Create a constant variable.
```kotlin
const val LOG = "android-retrofit"
```

Create a Retrofit client variable.
```kotlin
// The server code is in Common/Backend/code/HelloBackend9
private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:5000")
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

We need both gson and scalars converters. We need to put scalars converter in the earlier place before gson convert. Don’t switch them up.

Create an object from the Retrofit interface.
```kotlin
private val jwtAuthenticationApi = retrofit.create(JwtAuthentication::class.java)
```

Create a method to get the token, named getToken.
```kotlin
fun getToken() {
    val call = jwtAuthenticationApi.getToken(LoginRequest("john", "password"))
    thread {
        val token = call.execute().body()
        token?.get("access_token").let {
            Log.d(LOG, it)
            getSecret(it!!)
        }
    }
}
```

The json string in request’s body is given as LoginRequest object.
```kotlin
    val call = jwtAuthenticationApi.getToken(LoginRequest("john", "password"))
```

We get the value of map of string to string with key “access_token”.
```kotlin
token?.get("access_token").let {
```

Then we call the getSecret method which accepts token string parameter.
```kotlin
    getSecret(it!!)
```

Then we need to define a method to get the secret with the token.
```kotlin
fun getSecret(tokenString: String) {
    val jwtTokenString = "JWT " + tokenString
    val call = jwtAuthenticationApi.getSecret(jwtTokenString)
    val secret = call.execute().body()
    Log.d(LOG, secret)
}
```

We set the authentication header with the parameter to getSecret method.
```kotlin
    val jwtTokenString = "JWT " + tokenString
    val call = jwtAuthenticationApi.getSecret(jwtTokenString)
```

Don’t forget to execute getToken method inside onCreate method.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    getToken()
}
```

If we ran the application, we would get this output.
```
2019-12-28 16:12:16.919 13779-13822/com.example.helloretrofit3 D/android-retrofit: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1Nzc1MjQ2MzcsImlhdCI6MTU3NzUyNDMzNywibmJmIjoxNTc3NTI0MzM3LCJpZGVudGl0eSI6MX0.Xr4xq_EsdMG3sz5PbuqWPz06usRkkm5D4gCSbdPRLYY
2019-12-28 16:12:16.923 13779-13822/com.example.helloretrofit3 D/android-retrofit: User(id='1')
```

## Downloading Image

To get the image with Retrofit, we use ResponseBody return type.

Create a new empty Activity project, named HelloRetrofit4. The server code is in Common/Backend/code/HelloBackend2.

Create a Retrofit interface, app / java / com.example.helloretrofit4 / ImageApi.
```kotlin
package com.example.helloretrofit4

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ImageApi {

    @GET("/image")
    fun getImage(): Call<ResponseBody>
}
```

The return type of getImage method is `Call<ResponseBody>`. We decide not to parse the response body because the image is in binary format.

Edit app / res / layout / activity_main.xml. We want to put an image view in the middle of the screen.
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:srcCompat="@tools:sample/avatars[12]" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

Edit app / java / com.example.helloretrofit4 / MainActivity.

Add these import lines.
```kotlin
import android.widget.ImageView
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread
```

Create a variable for referencing the image view inside MainActivity.
```kotlin
private var imageView: ImageView? = null
```

Create a Retrofit client. We use scalars converter this time because an image is not in json format.
```kotlin
// The server code is in Common/Backend/code/HelloBackend2
private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:5000")
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()
```

Create an object from the Retrofit interface.
```kotlin
private val imageApi = retrofit.create(ImageApi::class.java)
```

Create a method to download the image. Name it downloadImage.
```kotlin
fun downloadImage() {
    val call = imageApi.getImage()
    thread {
        val imageBody = call.execute().body()
        val imageBytes = imageBody!!.byteStream()
        val image = BitmapFactory.decodeStream(imageBytes)
        runOnUiThread {
            imageView!!.setImageBitmap(image)
        }
    }
}
```

After getting the response body, we get the byte stream.
```kotlin
    val imageBody = call.execute().body()
    val imageBytes = imageBody!!.byteStream()
```

Then we reconstruct the image object.
```kotlin
val image = BitmapFactory.decodeStream(imageBytes)
```

We set the image bitmap to the image view in the UI thread.
```kotlin
runOnUiThread {
    imageView!!.setImageBitmap(image)
}
```

Get the reference to the image view and execute downloadImage method in onCreate method.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageView = findViewById(R.id.imageView)

    downloadImage()
}
```

If we ran the application, we would get the image on the screen.
<p align="center">
<img src="../Assets/ThirdPartyLibraries-Retrofit1.png">
</p>

## Uploading Image

We can upload a file using Retrofit.

Create an empty Activity project, named HelloRetrofit5. The server code is in Common/Backend/code/HelloBackend3.

Add okhttp3 library on top of Retrofit libraries in build.gradle (Module: app)
```gradle
implementation("com.squareup.okhttp3:okhttp:4.2.1")
```

We also need to add tree.jpg image in app / res / drawable / tree.jpg.

Create an interface, in app / java / com.example.helloretrofit5 / ImageApi.
```kotlin
package com.example.helloretrofit5

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ImageApi {

    @Multipart
    @POST("/upload_image")
    fun uploadImage(@Part image: MultipartBody.Part): Call<String>
}
```

The parameter that accepts the image is @Part image: MultipartBody.Part. We also have to annotate the method with @Multipart.

Edit app / java / com.example.helloretrofit5 / MainActivity.

Add these import lines.
```kotlin
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.ByteArrayOutputStream
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.concurrent.thread
```

Create a constant variable declaration for logging.
```kotlin
const val LOG = "android-retrofit"
```

Create a Retrofit client.
```kotlin
// The server code is in Common/Backend/code/HelloBackend3
private val retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:5000")
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()
```

Create an object from a Retrofit interface.
```kotlin
private val imageApi = retrofit.create(ImageApi::class.java)
```

Create a method to upload the image.
```kotlin
fun uploadImage() {
    val bitmapImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.tree)
    val stream = ByteArrayOutputStream()
    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, stream)
    val image = stream.toByteArray()

    val jpgMediaType = "image/jpg".toMediaTypeOrNull()
    val imageRequestBody = image.toRequestBody(jpgMediaType)

    val body = MultipartBody.Part.createFormData("file", "tree.jpg", imageRequestBody)

    val call = imageApi.uploadImage(body)
    thread {
        val result = call.execute().body()
        Log.d(LOG, result)
    }
}
```

We get the image’s bytes.
```kotlin
val bitmapImage = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.tree)
    val stream = ByteArrayOutputStream()
    bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, stream)
    val image = stream.toByteArray()
```

We convert the bytes to the request body.
```kotlin
    val jpgMediaType = "image/jpg".toMediaTypeOrNull()
    val imageRequestBody = image.toRequestBody(jpgMediaType)
```

Then we create a multipart form data.
```kotlin
    val body = MultipartBody.Part.createFormData("file", "tree.jpg", imageRequestBody)
```

Then we can execute uploadImage method with the multipart form data parameter.
```kotlin
    val call = imageApi.uploadImage(body)
```

Execute the call.
```kotlin
    val result = call.execute().body()
```

Don’t forget to execute uploadImage in onCreate method.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    uploadImage()
}
```

If we ran the application, we would get this output.
```
2019-12-29 15:28:42.571 16816-16857/com.example.helloretrofit5 D/android-retrofit: Hello Index!
```

You could check the file “tree.jpg” exists in “/tmp/uploaded_file/tree.jpg”.

# OkHttp3 Client and Download Progress

We can combine Retrofit with OkHttp3 client. Sometimes we want to do this to get the ability of getting download progress with OkHttp3.

Create a new empty Activity project, named HelloRetrofit6.

We need to use OkHttp3 library. Edit build.gradle (Module: app).
```gradle
implementation("com.squareup.okhttp3:okhttp:4.2.1")
```

Add an image view on the screen. Edit app / res / layout / activity_main.xml.
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ImageView
        android:id="@+id/imageView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:srcCompat="@tools:sample/avatars[12]" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

Create download progress interceptors in 3 classes.

The first one is app / java / com.example.helloretrofit6 / DownloadProgressListener.
```kotlin
package com.example.helloretrofit6

interface DownloadProgressListener {
    fun update(bytesRead: Long, contentLength: Long, done: Boolean)
}
```

The second one is app / java / com.example.helloretrofit6 / DownloadProgressResponseBody.
```kotlin
package com.example.helloretrofit6

import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException


class DownloadProgressResponseBody(val responseBody: ResponseBody, val downloadProgressListener: DownloadProgressListener): ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    override fun contentLength(): Long = responseBody.contentLength()

    override fun contentType(): MediaType? = responseBody.contentType()

    override fun source(): BufferedSource {
        if (bufferedSource == null)
            bufferedSource = getforwardSource(responseBody.source()).buffer()
        return bufferedSource!!
    }

    private fun getforwardSource(source: Source): Source =
        object : ForwardingSource(source) {
            var totalBytesRead = 0L

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)

                totalBytesRead += if (bytesRead != -1L) bytesRead else 0
                downloadProgressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1L)
                return bytesRead
            }
        }
}
```

The third one is app / java / com.example.helloretrofit6 / DownloadProgressInterceptor.
```kotlin 
package com.example.helloretrofit6

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


const val LOG = "android-networking"

class DownloadProgressInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val responseBuilder = originalResponse.newBuilder()

        val downloadProgressListener = object: DownloadProgressListener {
            override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
                if (done) {
                    Log.d(LOG, "Complete downloading item")
                } else {
                    Log.d(LOG, "${(bytesRead * 100) / contentLength}% done")
                }
            }
        }

        val downloadProgressBody = DownloadProgressResponseBody(originalResponse.body!!, downloadProgressListener)

        responseBuilder.body(downloadProgressBody)

        return responseBuilder.build()
    }
}
```

The full explanation of these download progress interceptors can be found in Android/Intermediate/Networking/doc/networking.odt.

Create a Retrofit interface, app / java / com.example.helloretrofit6 / ImageApi.
```kotlin
package com.example.helloretrofit6

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ImageApi {

    // Replace this url with something else, if the image does not exist anymore
    @GET("/photo-1573108037329-37aa135a142e?auto=format&fit=crop&w=800")
    fun getImage(): Call<ResponseBody>
}
```

Edit app / java / com.example.helloretrofit6 / MainActivity.

Add these import lines.
```kotlin
import android.widget.ImageView
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.concurrent.thread
```

Create a reference of the image view inside MainActivity.
```kotlin
private var imageView: ImageView? = null
```

Create a method to download the image.
```kotlin
fun downloadImageWithProgress() {
    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(DownloadProgressInterceptor())
        .build()

    // Replace this url with something else, if the image does not exist anymore
    val retrofit = Retrofit.Builder()
        .baseUrl("https://images.unsplash.com")
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val imageApi = retrofit.create(ImageApi::class.java)

    val call = imageApi.getImage()

    thread {
        val imageBody = call.execute().body()
        val imageBytes = imageBody!!.byteStream()
        val image = BitmapFactory.decodeStream(imageBytes)
        runOnUiThread {
            imageView!!.setImageBitmap(image)
        }
    }
}
```

We create OkHttp3 client.
```kotlin
val client = OkHttpClient.Builder()
    .addNetworkInterceptor(DownloadProgressInterceptor())
    .build()
```

Then we create a Retrofit client with this OkHttp3 client.
```kotlin
// Replace this url with something else, if the image does not exist anymore
val retrofit = Retrofit.Builder()
    .baseUrl("https://images.unsplash.com")
    .client(client)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()
```

We set the client of Retrofit client with “client” method.

Then we need to execute this method inside onCreate method. But of course we need to initialize our image view reference.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageView = findViewById(R.id.imageView)

    downloadImageWithProgress()
}
```

If we ran the application, we would get this screen.
<p align="center">
<img src="../Assets/ThirdPartyLibraries-Retrofit2.png">
</p>

We also would get this output.
```
2019-12-29 22:03:54.038 18898-18940/com.example.helloretrofit6 D/android-networking: 0% done
2019-12-29 22:03:54.084 18898-18940/com.example.helloretrofit6 D/android-networking: 2% done
2019-12-29 22:03:54.084 18898-18940/com.example.helloretrofit6 D/android-networking: 3% done
2019-12-29 22:03:54.084 18898-18940/com.example.helloretrofit6 D/android-networking: 4% done
2019-12-29 22:03:54.143 18898-18940/com.example.helloretrofit6 D/android-networking: 5% done
2019-12-29 22:03:54.143 18898-18940/com.example.helloretrofit6 D/android-networking: 6% done
2019-12-29 22:03:54.143 18898-18940/com.example.helloretrofit6 D/android-networking: 7% done
2019-12-29 22:03:54.143 18898-18940/com.example.helloretrofit6 D/android-networking: 8% done
2019-12-29 22:03:54.143 18898-18940/com.example.helloretrofit6 D/android-networking: 10% done
2019-12-29 22:03:54.144 18898-18940/com.example.helloretrofit6 D/android-networking: 13% done
2019-12-29 22:03:54.144 18898-18940/com.example.helloretrofit6 D/android-networking: 13% done
... 
2019-12-29 22:03:54.654 18898-18940/com.example.helloretrofit6 D/android-networking: 79% done
2019-12-29 22:03:54.661 18898-18940/com.example.helloretrofit6 D/android-networking: 80% done
2019-12-29 22:03:54.664 18898-18940/com.example.helloretrofit6 D/android-networking: 81% done
2019-12-29 22:03:54.664 18898-18940/com.example.helloretrofit6 D/android-networking: 81% done
2019-12-29 22:03:54.690 18898-18940/com.example.helloretrofit6 D/android-networking: 83% done
2019-12-29 22:03:54.690 18898-18940/com.example.helloretrofit6 D/android-networking: 84% done
2019-12-29 22:03:54.703 18898-18940/com.example.helloretrofit6 D/android-networking: 85% done
2019-12-29 22:03:54.714 18898-18940/com.example.helloretrofit6 D/android-networking: 86% done
2019-12-29 22:03:54.726 18898-18940/com.example.helloretrofit6 D/android-networking: 87% done
2019-12-29 22:03:54.745 18898-18940/com.example.helloretrofit6 D/android-networking: 88% done
2019-12-29 22:03:54.747 18898-18940/com.example.helloretrofit6 D/android-networking: 90% done
2019-12-29 22:03:54.757 18898-18940/com.example.helloretrofit6 D/android-networking: 91% done
2019-12-29 22:03:54.757 18898-18940/com.example.helloretrofit6 D/android-networking: 92% done
2019-12-29 22:03:54.776 18898-18940/com.example.helloretrofit6 D/android-networking: 93% done
2019-12-29 22:03:54.780 18898-18940/com.example.helloretrofit6 D/android-networking: 94% done
2019-12-29 22:03:54.790 18898-18940/com.example.helloretrofit6 D/android-networking: 95% done
2019-12-29 22:03:54.791 18898-18940/com.example.helloretrofit6 D/android-networking: 96% done
2019-12-29 22:03:54.807 18898-18940/com.example.helloretrofit6 D/android-networking: 98% done
2019-12-29 22:03:54.808 18898-18940/com.example.helloretrofit6 D/android-networking: 99% done
2019-12-29 22:03:54.818 18898-18940/com.example.helloretrofit6 D/android-networking: 100% done
2019-12-29 22:03:54.818 18898-18940/com.example.helloretrofit6 D/android-networking: Complete downloading item
```

# Optional Readings

https://square.github.io/retrofit/

# Exercises

1. Convert 3 of these Java examples to Kotlin code. https://github.com/square/retrofit/tree/master/samples/src/main/java/com/example/retrofit