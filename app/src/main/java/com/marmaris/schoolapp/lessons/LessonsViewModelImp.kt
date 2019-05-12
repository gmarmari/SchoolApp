package com.marmaris.schoolapp.lessons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marmaris.schoolapp.BaseViewModel
import com.marmaris.schoolapp.data.lessons.Lesson
import com.marmaris.schoolapp.data.lessons.LessonsDs
import com.marmaris.schoolapp.data.lessons.LessonsRepo
import com.marmaris.schoolapp.di.FragmentScoped
import java.lang.Error
import javax.inject.Inject

@FragmentScoped
class LessonsViewModelImp @Inject constructor(lessonsRepo: LessonsRepo) : BaseViewModel(), LessonsViewModel {

    private val mLessonsRepo: LessonsRepo = lessonsRepo
    private val mLessons = MutableLiveData<List<Lesson>>().apply { value = emptyList() }
    private var mIsLoading = MutableLiveData<Boolean>().apply { value = false }

    //region LessonsViewModel

    override fun getLessons(): LiveData<List<Lesson>> {
        return mLessons
    }

    override fun isLoading(): LiveData<Boolean> {
        return mIsLoading
    }

    override fun loadLessons() {
        mIsLoading.apply { value = true }
        mLessons.apply { value = ArrayList() }
        mLessonsRepo.getLessons(object : LessonsDs.GetLessonsCallback{
            override fun onSuccess(lessons: List<Lesson>) {
                mIsLoading.apply { value = false }
                mLessons.apply { value = lessons }
            }

            override fun onError(error: Error) {
                mIsLoading.apply { value = false }
            }

        })
    }

    override fun onRefresh() {
        loadLessons()
    }

    //endregion LessonsViewModel


}