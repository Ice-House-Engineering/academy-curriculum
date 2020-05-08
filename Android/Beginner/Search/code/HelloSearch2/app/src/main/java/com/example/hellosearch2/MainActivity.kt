package com.example.hellosearch2

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.se.omapi.SEService
import android.view.Menu
import android.widget.SearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search).actionView as SearchView
        searchView.apply {
            val component = ComponentName(context, SearchableActivity::class.java)
            setSearchableInfo(searchManager.getSearchableInfo(component))
        }

        return true
    }
}
