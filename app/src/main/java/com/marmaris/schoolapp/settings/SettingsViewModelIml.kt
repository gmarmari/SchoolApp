package com.marmaris.schoolapp.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marmaris.schoolapp.BaseViewModel
import com.marmaris.schoolapp.di.PreferencesRepo
import com.marmaris.schoolapp.di.ResourcesRepo
import javax.inject.Inject

class SettingsViewModelIml @Inject constructor(
    private val mResourcesRepo : ResourcesRepo,
    private val mPreferencesRepo: PreferencesRepo) : BaseViewModel(), SettingsViewModel {

    private val mListItems = MutableLiveData<List<SettingsItem>>().apply { value = emptyList() }

    override fun getListItems(): LiveData<List<SettingsItem>> {
        return mListItems
    }

    override fun createListItems() {
        val items = ArrayList<SettingsItem>()
        items.add(getItemOnlineMode())
        mListItems.apply {
            value = items
        }
    }

    override fun getItemOnlineMode(): SettingsItemOnOff {
        val mItem = SettingsItemOnOff()
        mItem.title = mResourcesRepo.stringOnlineMode
        mItem.description = mResourcesRepo.stringOnlineModeDescription
        mItem.imageRes = mResourcesRepo.imageWeb
        mItem.isOn = mPreferencesRepo.getRemoteUrl().isNotEmpty()
        return mItem
    }

}