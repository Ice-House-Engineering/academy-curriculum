package com.example.hellodi5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


const val LOG = "hellodi-log"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }

        val scopeA = getKoin().createScope("scopeA", named("Scope A"))
        val helloRepositoryImpl = scopeA.get<HelloRepositoryImpl>()
        helloRepositoryImpl.hello = "Hola"
        Log.d(LOG, helloRepositoryImpl.giveHello())

        val helloRepositoryImplSame = scopeA.get<HelloRepositoryImpl>()
        Log.d(LOG, helloRepositoryImplSame.giveHello())

        val scopeA2 = getKoin().createScope("scopeA2", named("Scope A"))
        val helloRepositoryImpl2 = scopeA2.get<HelloRepositoryImpl>()
        Log.d(LOG, helloRepositoryImpl2.giveHello())

        val scopeB : Scope = getKoin().createScope("scopeB", named("Scope B"))
        val helloRepositoryTestImpl = scopeB.get<HelloRepositoryTestImpl>()
        Log.d(LOG, helloRepositoryTestImpl.giveHello())


    }
}
