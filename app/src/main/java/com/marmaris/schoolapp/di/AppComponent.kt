package com.marmaris.schoolapp.di

import dagger.android.AndroidInjector
import com.marmaris.schoolapp.MyApplication
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * This is a Dagger component. Refer to {@link ToDoApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * ToDoApplication}.
 * //{@link AndroidSupportInjectionModule}
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ActivityBindingModule::class,
    FragmentBindingModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

     // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
     // never having to instantiate any modules or say which module we are passing the application to.
     // Application will just be provided into our app graph now.
     @Component.Builder
     interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}