package com.example.hellodatapersistence1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.*

const val LOG = "data-persistence"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readRaw()
        readAssets()
        writeToInternalStorage()
        readFromInternalStorage()
        createDirectoryInsideInternalStorage()
        writeUsingContext()
        readUsingContext()
        writeAndReadTmpFile()
    }

    fun readRaw() {
        val inputStream1 = resources.openRawResource(R.raw.file1)
        val content = StringBuilder()
        val reader = inputStream1.reader()
        val buffered = reader.buffered()
        do {
            val line = buffered.readLine()
            if (line!=null)
                content.append(line)
        } while (line != null)
        buffered.close()
        reader.close()
        inputStream1.close()
        Log.d(LOG, "Reading from raw folder")
        Log.d(LOG, content.toString())
    }

    fun readAssets() {
        val inputStream1 = assets.open("file2.txt")
        inputStream1.reader().use { reader ->
            val content = reader.buffered().use { it.readText() }
            Log.d(LOG, "Reading from assets folder")
            Log.d(LOG, content)
        }
    }

    fun writeToInternalStorage() {
        val file1 = File(applicationContext.filesDir, "file3")
        val outputStream1 = FileOutputStream(file1)
        val outputStreamWriter1 = OutputStreamWriter(outputStream1)
        val writer1 = BufferedWriter(outputStreamWriter1)

        writer1.write("Line1.\n")
        writer1.write("Line2.\n")
        writer1.write("Line3.\n")
        writer1.write("Line4.\n")
        writer1.flush()
        outputStream1.fd.sync()
        writer1.close()
        outputStreamWriter1.close()
        outputStream1.close()
    }

    fun readFromInternalStorage() {
        val file1 = File(applicationContext.filesDir, "file3")
        val inputStream1 = FileInputStream(file1)
        inputStream1.reader().use { reader ->
            val content = reader.buffered().use { it.readText() }
            Log.d(LOG, "Reading from internal storage")
            Log.d(LOG, content)
        }
    }

    fun createDirectoryInsideInternalStorage() {
        val dir1 = File(applicationContext.filesDir, "dir2")
        if (!dir1.exists())
            dir1.mkdir()
    }

    fun readUsingContext() {
        applicationContext.openFileInput("file4").use { inputStream ->
            val text = inputStream.bufferedReader().use {
                it.readText()
            }
            Log.d(LOG, "Reading using application context")
            Log.d(LOG, text)
        }
    }

    fun writeUsingContext() {
        applicationContext.openFileOutput("file4", Context.MODE_PRIVATE).use { outputStream ->
            outputStream.write("The content of file4, written using application context.".toByteArray())
        }
    }

    fun writeAndReadTmpFile() {
        val filetmp = File.createTempFile("filetmp", ".tmp")
        val outputStream1 = FileOutputStream(filetmp)
        val outputStreamWriter1 = OutputStreamWriter(outputStream1)
        val writer1 = BufferedWriter(outputStreamWriter1)

        writer1.write("The content of the temporary file: filetmp.")
        writer1.flush()
        outputStream1.fd.sync()
        writer1.close()
        outputStreamWriter1.close()
        outputStream1.close()

        val inputStream1 = FileInputStream(filetmp)
        val reader = inputStream1.reader()
        val content = reader.buffered().use { it.readText() }
        Log.d(LOG, "Reading from cache directory")
        Log.d(LOG, content)
    }

}
