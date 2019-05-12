package com.marmaris.schoolapp.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun AppCompatActivity.replaceFragmentInActivity(fragment: Fragment, frameId: Int) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment)
        }.commit()
    }
