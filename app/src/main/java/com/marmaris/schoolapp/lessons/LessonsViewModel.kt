package com.marmaris.schoolapp.lessons

import androidx.lifecycle.LiveData
import com.marmaris.schoolapp.data.lessons.Lesson
import java.lang.Error

interface LessonsViewModel {

    fun getLessons() : LiveData<List<Lesson>>
    fun isLoading() : LiveData<Boolean>
    fun getError() : LiveData<Error>
    fun loadLessons()
    fun onRefresh()
}