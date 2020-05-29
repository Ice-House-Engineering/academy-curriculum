package com.example.hellorecyclerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    val images = arrayOf("anna", "boim", "boxed", "chander", "daniel", "devon", "omid", "tanner")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = GridLayoutManager(this, 2)
        viewAdapter = ImageAdapter(images)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        // Different number of cards per row, comment it out to get the default behavior
        (viewManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(p0: Int): Int {
                if (p0 == 0 || p0 == 1 || p0 == 3 || p0 == 4) {
                    return 1
                }
                return 2
            }
        }
    }
}
