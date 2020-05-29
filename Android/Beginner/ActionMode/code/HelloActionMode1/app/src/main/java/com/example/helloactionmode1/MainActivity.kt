package com.example.helloactionmode1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.view.ActionMode

const val tag = "action-mode-tag"


class MainActivity : AppCompatActivity(), ActionMode.Callback {

    private lateinit var actionMode : ActionMode
    private var beginningActionMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            actionMode = startSupportActionMode(this)!!
        }

        findViewById<Button>(R.id.buttonInvalidate).setOnClickListener {
            beginningActionMode = false
            actionMode.invalidate()
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
        Log.d(tag, "Shutting down Action Mode")
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        if (beginningActionMode) {
            return false
        }
        menu?.clear()
        menuInflater.inflate(R.menu.actions_invalidate, menu)
        mode!!.title = "ActionMode after Invalidation"
        beginningActionMode = true
        return true
    }
}
