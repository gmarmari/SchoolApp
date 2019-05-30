package com.marmaris.schoolapp.lessons

import dagger.Module
import dagger.Binds
import dagger.Provides
import com.marmaris.schoolapp.data.AppDatabase
import com.marmaris.schoolapp.data.lessons.LessonsDsLocal
import com.marmaris.schoolapp.data.lessons.LessonsDsRemote
import com.marmaris.schoolapp.data.lessons.LessonsRepo
import com.marmaris.schoolapp.di.FragmentScoped
import com.marmaris.schoolapp.di.PreferencesRepoProd
import com.marmaris.schoolapp.util.AppExecutors


@Suppress("unused")
@Module
abstract class LessonsModule {

    @Module
     companion object {

        @JvmStatic
        @FragmentScoped
        @Provides
        fun provideLessonsRepository(fragment: LessonsFragment) : LessonsRepo {
            val baseUrl = PreferencesRepoProd(fragment.activity!!).getRemoteUrl()
            return if (baseUrl.isNotEmpty()) {
                LessonsRepo.getInstance(LessonsDsRemote(AppExecutors(), baseUrl),
                    LessonsDsLocal.getInstance(AppExecutors(), AppDatabase.getInstance(fragment.activity!!).lessonDao()))
            } else {
                LessonsRepo.getInstance(LessonsDsLocal.getInstance(AppExecutors(), AppDatabase.getInstance(fragment.activity!!).lessonDao()))
            }
        }
    }

    @FragmentScoped
    @Binds
    abstract fun lessonsViewModel(viewModel: LessonsViewModelImp): LessonsViewModel

}

