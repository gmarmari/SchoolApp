package com.marmaris.schoolapp.lessons

import dagger.Module
import dagger.Binds
import dagger.Provides
import com.marmaris.schoolapp.data.AppDatabase
import com.marmaris.schoolapp.data.lessons.LessonsDsLocal
import com.marmaris.schoolapp.data.lessons.LessonsDsRemote
import com.marmaris.schoolapp.data.lessons.LessonsRepo
import com.marmaris.schoolapp.di.FragmentScoped
import com.marmaris.schoolapp.util.AppExecutors


@Module
abstract class LessonsModule {

    @Module
     companion object {

        @JvmStatic
        @FragmentScoped
        @Provides
        fun provideLessonsRepository(fragment: LessonsFragment) = LessonsRepo.getInstance(
            LessonsDsRemote(),
            LessonsDsLocal.getInstance(AppExecutors(), AppDatabase.getInstance(fragment.activity!!).lessonDao())
        )

    }

    @FragmentScoped
    @Binds
    abstract fun lessonsViewModel(viewModel: LessonsViewModelImp): LessonsViewModel

}