package com.marmaris.schoolapp.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.marmaris.schoolapp.lessons.LessonsFragment
import com.marmaris.schoolapp.lessons.LessonsModule
import com.marmaris.schoolapp.settings.SettingsFragment
import com.marmaris.schoolapp.settings.SettingsModule

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Suppress("unused")
@Module
abstract class FragmentBindingModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = [LessonsModule::class])
    abstract fun lessonsFragment(): LessonsFragment

    @FragmentScoped
    @ContributesAndroidInjector(modules = [SettingsModule::class])
    abstract fun settingsFragment(): SettingsFragment

}