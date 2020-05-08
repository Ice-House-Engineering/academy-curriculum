package com.example.hellodrawer1

import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.transaction
import com.example.hellodrawer1.fragments.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                supportFragmentManager.transaction {
                    replace(R.id.fragmentDrawer, HomeFragment())
                }
            }
            R.id.nav_gallery -> {
                supportFragmentManager.transaction {
                    replace(R.id.fragmentDrawer, GalleryFragment())
                }
            }
            R.id.nav_slideshow -> {
                supportFragmentManager.transaction {
                    replace(R.id.fragmentDrawer, SlideshowFragment())
                }
            }
            R.id.nav_tools -> {
                supportFragmentManager.transaction {
                    replace(R.id.fragmentDrawer, ToolsFragment())
                }
            }
            R.id.nav_share -> {
                supportFragmentManager.transaction {
                    replace(R.id.fragmentDrawer, ShareFragment())
                }
            }
            R.id.nav_send -> {
                supportFragmentManager.transaction {
                    replace(R.id.fragmentDrawer, SendFragment())
                }
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
