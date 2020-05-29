package com.example.hellosearch1

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.apply {
            val component = ComponentName(context, SearchableActivity::class.java)
            setSearchableInfo(searchManager.getSearchableInfo(component))
        }

        val textViewSearchView = findViewById<TextView>(R.id.textViewSearchView)
        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(p0: String?): Boolean {
                    textViewSearchView.text = p0!!.toUpperCase()
                    return true
                }

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }
            }
        )
    }

}
