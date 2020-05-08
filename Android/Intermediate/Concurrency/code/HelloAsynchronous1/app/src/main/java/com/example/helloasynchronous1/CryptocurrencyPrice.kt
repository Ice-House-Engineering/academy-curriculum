package com.example.helloasynchronous1

import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference


const val LOG = "android-asynctask"

class CryptocurrencyPrice(val textView: TextView, val progressBar: ProgressBar) : AsyncTask<String, Int, Int>() {

    val cryptocurrencies = mapOf("Bitcoin" to 11000, "Ethereum" to 180)
    val weakTextView : WeakReference<TextView>? = WeakReference(textView)
    val weakProgressBar : WeakReference<ProgressBar>? = WeakReference(progressBar)

    override fun onPreExecute() {
        weakTextView?.get()?.text = "Calculating the price of cryptocurrency"
    }

    override fun doInBackground(vararg inputs: String): Int {
        publishProgress(25)
        Thread.sleep(300)
        val price = cryptocurrencies[inputs[0]]
        publishProgress(90)
        Thread.sleep(300)
        publishProgress(100)

        return price!!
    }

    override fun onProgressUpdate(vararg values: Int?) {
        weakProgressBar?.get()?.progress = values[0]!!
        Log.d(LOG, "Progress ${values[0]}")
    }

    override fun onPostExecute(price: Int) {
        weakTextView?.get()?.text = "Price: $price"
        Log.d(LOG, "Price $price")
    }
}