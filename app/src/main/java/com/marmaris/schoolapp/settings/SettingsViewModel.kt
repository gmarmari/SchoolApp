package com.marmaris.schoolapp.settings

import androidx.lifecycle.LiveData

interface SettingsViewModel {

    fun getListItems() : LiveData<List<SettingsItem>>
    fun createListItems()

    fun getItemOnlineMode() : SettingsItemOnOff


}