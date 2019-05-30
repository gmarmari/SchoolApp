package com.marmaris.schoolapp.di


/**
 * Interface for the Shared Preferences of the app
 **/
interface PreferencesRepo {

    fun getRemoteUrl() : String
    fun setRemoteUrl(value : String)

}