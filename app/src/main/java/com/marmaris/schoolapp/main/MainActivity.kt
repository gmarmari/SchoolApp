package com.marmaris.schoolapp.main

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import com.marmaris.schoolapp.BaseActivity
import com.marmaris.schoolapp.R
import kotlinx.android.synthetic.main.activity_main.*
import com.marmaris.schoolapp.lessons.LessonsFragment
import com.marmaris.schoolapp.util.replaceFragmentInActivity

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setUpMainFragment()

        fab.setOnClickListener { view ->

            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.main_nav_person -> {
                setUpMainFragment()
            }
            R.id.main_nav_lessons -> {
                setUpLessonsFragment()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setUpMainFragment() {
        if (supportFragmentManager.findFragmentById(R.id.content_frame) !is MainFragment)
            replaceFragmentInActivity(MainFragment.newInstance(), R.id.content_frame)
    }

    private fun setUpLessonsFragment() {
        if (supportFragmentManager.findFragmentById(R.id.content_frame) !is LessonsFragment)
            replaceFragmentInActivity(LessonsFragment.newInstance(), R.id.content_frame)
    }

}
