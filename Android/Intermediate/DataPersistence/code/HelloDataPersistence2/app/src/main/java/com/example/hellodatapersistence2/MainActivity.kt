package com.example.hellodatapersistence2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

const val LOG = "data-persistence"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        writeToExternalStorage()
    }

    fun writeToExternalStorage() {
        val file1 = File(applicationContext.getExternalFilesDir(null), "file1")

        FileOutputStream(file1).use { outputStream ->
            OutputStreamWriter(outputStream).use { outputStreamWriter ->
                BufferedWriter(outputStreamWriter).use { bufferedWriter ->
                    bufferedWriter.write("Content of file1.\n")
                    outputStream.fd.sync()
                }
            }
        }
    }

}
