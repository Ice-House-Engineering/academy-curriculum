package com.example.helloasynchronous2

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Operation
import androidx.work.WorkInfo
import androidx.work.WorkManager
import org.hamcrest.CoreMatchers.`is`

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


// https://developer.android.com/topic/libraries/architecture/workmanager/how-to/testing
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun testWorker() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val request = OneTimeWorkRequestBuilder<BasicTrulySimpleWorker>().build()

        val workManager = WorkManager.getInstance(appContext)
        workManager.enqueue(request)

        Thread.sleep(1600)

        val workInfo = workManager.getWorkInfoById(request.id).get()
        assertThat(workInfo.state, `is`(WorkInfo.State.SUCCEEDED))
    }
}
