package com.example.hellorecyclerview4

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
            val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
            addItemDecoration(SpacingItemDecoration(2, spacing))
        }
    }
}
