package com.marmaris.schoolapp.di

import android.content.Context
import android.content.SharedPreferences

/**
 * Productive implementation of the PreferencesRepo Interface.
 * Manages the real Shared Preferences of the app.
 **/
class PreferencesRepoProd(private val mContext : Context) : PreferencesRepo {

    companion object{
        private const val NAME = "com.marmaris.schoolapp.di.PreferencesRepoProd"
        private const val PREF_REMOTE_URL = "PREF_REMOTE_URL"
    }

    private fun getPrefs() : SharedPreferences{
        return mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    //region PreferencesRepo

    override fun getRemoteUrl(): String {
        return getPrefs().getString(PREF_REMOTE_URL, "") ?: ""
    }

    override fun setRemoteUrl(value: String) {
        val editor = getPrefs().edit()
        editor.putString(PREF_REMOTE_URL, value)
        editor.apply()
    }

    //endregion PreferencesRepo

}