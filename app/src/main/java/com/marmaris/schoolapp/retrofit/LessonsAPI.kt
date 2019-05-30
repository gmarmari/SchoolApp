@file:Suppress("unused")

package com.marmaris.schoolapp.retrofit

import com.marmaris.schoolapp.data.lessons.Lesson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST


interface LessonsAPI {

    @GET("api/lessons/get")
    fun getAllLessons(): Call<List<Lesson>>

    @POST("api/lessons/add")
    fun addLesson(lesson : Lesson): Call<List<Lesson>>

}