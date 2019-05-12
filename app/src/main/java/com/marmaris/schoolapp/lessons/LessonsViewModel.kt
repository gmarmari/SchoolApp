package com.marmaris.schoolapp.lessons

import androidx.lifecycle.LiveData
import com.marmaris.schoolapp.data.lessons.Lesson

interface LessonsViewModel {

    fun getLessons() : LiveData<List<Lesson>>
    fun isLoading() : LiveData<Boolean>
    fun loadLessons()
    fun onRefresh()
}