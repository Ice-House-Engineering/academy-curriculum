package com.example.helloactionmode2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.view.ActionMode
import androidx.appcompat.widget.Toolbar

const val tag = "action-mode-tag"


class MainActivity : AppCompatActivity(), ActionMode.Callback {

    private lateinit var actionMode : ActionMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        findViewById<Button>(R.id.button).setOnClickListener {
            actionMode = toolbar.startActionMode(this)!!
        }
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.newAction -> Log.d(tag, "New Action")
            R.id.downloadAction -> actionMode.finish()
            R.id.loginAction -> Log.d(tag, "Login Action")
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        mode!!.title = "Title of ActionMode"
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }
}
