# Moshi

Moshi is a json library, like GSON.

For Moshi projects, we want to add these dependencies.

Edit build.gradle (Module: app). Add these dependencies.
```gradle
implementation("com.squareup.moshi:moshi:1.9.2")
implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
kapt("com.squareup.moshi:moshi-kotlin-codegen:1.9.2")
```

## Basic Usage

Create a new empty Activity project and name it HelloMoshi1.

Create a class that we want to serialize in json, app / java / com.example.hellomoshi1 / Cryptocurrency.
```kotlin
package com.example.hellomoshi1

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Cryptocurrency(
    val name: String,
    val price: Int,
    val marketcap: Int?,
    val forks: List<Fork>?
)
```

We have to define Fork class, app / java / com.example.hellomoshi1 / Fork.
```kotlin
package com.example.hellomoshi1

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Fork(
    val name: String,
    val price: Int
)
```

We have a class which has three properties: String, Int, optional String, and list of objects.

Edit app / java / com.example.hellomoshi1 / MainActivity.
```kotlin
package com.example.hellomoshi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.Moshi


const val TAG = "hello-moshi"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Converting from object to json
        val forkBitcoinCash = Fork("Bitcoin Cash", 220)
        val forkBitcoinSV = Fork("Bitcoin SV", 79)
        val cryptocurrency = Cryptocurrency("Bitcoin", 6633, 121313, listOf(forkBitcoinCash, forkBitcoinSV))

        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(cryptocurrency.javaClass)
        val jsonResult = adapter.toJson(cryptocurrency)
        Log.d(TAG, jsonResult)

        // Converting from json to object
        val cryptocurrency2 = adapter.fromJson(jsonResult)
        Log.d(TAG, cryptocurrency2.toString())
    }
}
```

We create an object that we want to serialize.
```kotlin
val cryptocurrency = Cryptocurrency("Bitcoin", 6633, 121313)
```

We build a Moshi object.
```kotlin
val moshi = Moshi.Builder().build()
```

We create an adapter. Adapter is the one which is responsible for converting from json to object or vice versa.
```kotlin
val adapter = moshi.adapter(cryptocurrency.javaClass)
```

Then the conversion from object to json happens through “toJson” method.
```kotlin
val jsonResult = adapter.toJson(cryptocurrency)
```

The conversion from json to object happens through “fromJson” method.
```kotlin
val cryptocurrency2 = adapter.fromJson(jsonResult)
```

If we ran the application, we would get this output.
```
2020-03-26 16:49:57.691 28025-28025/com.example.hellomoshi1 D/hello-moshi: {"name":"Bitcoin","price":6633,"marketcap":121313,"forks":[{"name":"Bitcoin Cash","price":220},{"name":"Bitcoin SV","price":79}]}
2020-03-26 16:49:57.696 28025-28025/com.example.hellomoshi1 D/hello-moshi: Cryptocurrency(name=Bitcoin, price=6633, marketcap=121313, forks=[Fork(name=Bitcoin Cash, price=220), Fork(name=Bitcoin SV, price=79)])
```

## Custom Adapter

If we want to override the process of converting json to object or vice versa, we can use custom adapter.

We are still using the same project. Create a custom adapter class, app / java / com.example.hellomoshi1 / CryptocurrencyAdapter.
```kotlin
package com.example.hellomoshi1

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson


class CryptocurrencyAdapter {

    @ToJson
    fun toJson(cryptocurrency: Cryptocurrency) : String {
        return cryptocurrency.name
    }

    @FromJson
    fun fromJson(json: String) : Cryptocurrency {
        return Cryptocurrency(json, 0, null, null)
    }
}
```

We need to annotate the methods which we want to convert from object to json with @ToJson annotation and we want to convert from json to object with @FromJson.

Then edit MainActivity. Add these lines.
```kotlin
// Converting from object to json with custom adapter
val customMoshi = Moshi.Builder().add(CryptocurrencyAdapter()).build()
val customAdapter = customMoshi.adapter(cryptocurrency.javaClass)
val customJsonResult = customAdapter.toJson(cryptocurrency)

Log.d(TAG, customJsonResult)

// Converting from json to object with custom adapter
val cryptocurrencyWithCustomAdapter = customAdapter.fromJson(customJsonResult)

Log.d(TAG, cryptocurrencyWithCustomAdapter.toString())
```

We use our custom adapter when building our Moshi object.
```kotlin
val customMoshi = Moshi.Builder().add(CryptocurrencyAdapter()).build()
```

Then the rest is same.

If we ran the application, we would get this log for our custom adapter.
```
2020-03-26 18:27:31.508 29037-29037/com.example.hellomoshi1 D/hello-moshi: "Bitcoin"
2020-03-26 18:27:31.508 29037-29037/com.example.hellomoshi1 D/hello-moshi: Cryptocurrency(name=Bitcoin, price=0, marketcap=null, forks=null)
```

## Parsing Date

If we want to parse a date, we need to install optional Moshi adapter. Edit build.gradle (Module: app).

Add this dependency.
```gradle
implementation("com.squareup.moshi:moshi-adapters:1.9.2")
```

Edit app / java / com.example.hellomoshi1 / Cryptocurrency. Add a Date field.
```kotlin
val created: Date?
```

The complete file looks like this.
```kotlin
package com.example.hellomoshi1

import com.squareup.moshi.JsonClass
import java.util.*


@JsonClass(generateAdapter = true)
data class Cryptocurrency(
    val name: String,
    val price: Int,
    val marketcap: Int?,
    val forks: List<Fork>?,
    val created: Date?
)
```

Edit app / java / com.example.hellomoshi1 / CryptocurrencyAdapter. Add a value for Date field.
```kotlin
return Cryptocurrency(json, 0, null, null, created=null)
```

The complete file looks like this.
```kotlin
package com.example.hellomoshi1

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson


class CryptocurrencyAdapter {

    @ToJson
    fun toJson(cryptocurrency: Cryptocurrency) : String {
        return cryptocurrency.name
    }

    @FromJson
    fun fromJson(json: String) : Cryptocurrency {
        return Cryptocurrency(json, 0, null, null, created=null)
    }
}
```

Edit app / java / com.example.hellomoshi1 / MainActivity. This is the complete file.
```kotlin
package com.example.hellomoshi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.*


const val TAG = "hello-moshi"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Converting from object to json
        val forkBitcoinCash = Fork("Bitcoin Cash", 220)
        val forkBitcoinSV = Fork("Bitcoin SV", 79)
        val currentDate = Date()
        val cryptocurrency = Cryptocurrency("Bitcoin", 6633, 121313, listOf(forkBitcoinCash, forkBitcoinSV), currentDate)

        val moshi = Moshi.Builder().add(currentDate.javaClass, Rfc3339DateJsonAdapter()).build()
        val adapter = moshi.adapter(cryptocurrency.javaClass)
        val jsonResult = adapter.toJson(cryptocurrency)
        Log.d(TAG, jsonResult)

        // Converting from json to object
        val cryptocurrency2 = adapter.fromJson(jsonResult)
        Log.d(TAG, cryptocurrency2.toString())

        // Converting from object to json with custom adapter
        val customMoshi = Moshi.Builder().add(CryptocurrencyAdapter()).build()
        val customAdapter = customMoshi.adapter(cryptocurrency.javaClass)
        val customJsonResult = customAdapter.toJson(cryptocurrency)
        Log.d(TAG, customJsonResult)

        // Converting from json to object with custom adapter
        val cryptocurrencyWithCustomAdapter = customAdapter.fromJson(customJsonResult)
        Log.d(TAG, cryptocurrencyWithCustomAdapter.toString())
    }
}
```

We create a Cryptocurrency object with Date field.
```kotlin
    val currentDate = Date()
    val cryptocurrency = Cryptocurrency("Bitcoin", 6633, 121313, listOf(forkBitcoinCash, forkBitcoinSV), currentDate)
```

We use Rfc3339DateJsonAdapter when building Moshi object.
```kotlin
    val moshi = Moshi.Builder().add(currentDate.javaClass, Rfc3339DateJsonAdapter()).build()
```

The first parameter of “add” method from the builder is the class of the Date. The second parameter is the Rfc3339DateJsonAdapter.

The rest of the code is same.

If we ran the application, we would get this log which display date field.
```
2020-03-26 21:00:07.553 30498-30498/com.example.hellomoshi1 D/hello-moshi: {"name":"Bitcoin","price":6633,"marketcap":121313,"forks":[{"name":"Bitcoin Cash","price":220},{"name":"Bitcoin SV","price":79}],"created":"2020-03-26T14:00:07.518Z"}
2020-03-26 21:00:07.582 30498-30498/com.example.hellomoshi1 D/hello-moshi: Cryptocurrency(name=Bitcoin, price=6633, marketcap=121313, forks=[Fork(name=Bitcoin Cash, price=220), Fork(name=Bitcoin SV, price=79)], created=Thu Mar 26 21:00:07 GMT+07:00 2020)
```

# Optional Readings

https://github.com/square/moshi

# Exercises

1. Convert 3 of these Java examples to Kotlin code. https://github.com/square/moshi/tree/master/examples/src/main/java/com/squareup/moshi/recipes