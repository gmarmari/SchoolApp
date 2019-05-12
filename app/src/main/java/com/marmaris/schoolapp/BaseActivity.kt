package com.marmaris.schoolapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {


    protected fun goBack(){
        finish()
    }

}