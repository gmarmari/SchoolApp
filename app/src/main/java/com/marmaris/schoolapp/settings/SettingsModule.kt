package com.marmaris.schoolapp.settings

import com.marmaris.schoolapp.di.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Suppress("unused")
@Module
abstract class SettingsModule {

    @Module
    companion object {

        @JvmStatic
        @FragmentScoped
        @Provides
        fun providePreferencesRepo(fragment: SettingsFragment) : PreferencesRepo = PreferencesRepoProd(fragment.activity!!)

        @JvmStatic
        @FragmentScoped
        @Provides
        fun provideResourcesRepo(fragment: SettingsFragment) : ResourcesRepo = ResourcesRepoProd(fragment.activity!!)

    }

    @FragmentScoped
    @Binds
    abstract fun settingsViewModel(viewModel: SettingsViewModelIml): SettingsViewModel

}