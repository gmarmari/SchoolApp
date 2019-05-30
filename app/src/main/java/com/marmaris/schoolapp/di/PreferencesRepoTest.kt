package com.marmaris.schoolapp.di

/**
 * Test implementation of the PreferencesRepo Interface.
 * Used in unit testing.
 **/
@Suppress("unused")
class PreferencesRepoTest : PreferencesRepo {

    //region PreferencesRepo

    private var mUrl = ""
    override fun getRemoteUrl(): String {
        return mUrl
    }

    override fun setRemoteUrl(value: String) {
        mUrl = value
    }

    //endregion PreferencesRepo

}