package com.marmaris.schoolapp.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.marmaris.schoolapp.data.lessons.Lesson
import com.marmaris.schoolapp.data.lessons.LessonsDs
import java.lang.Error
import java.lang.Exception

class LessonsGetController : retrofit2.Callback<List<Lesson>> {

    private var mCallback : LessonsDs.GetLessonsCallback? = null

    fun start(baseUrl: String, callback: LessonsDs.GetLessonsCallback) {
        mCallback = callback

        var mLessonsAPI : LessonsAPI? = null
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
            mLessonsAPI = retrofit.create<LessonsAPI>(LessonsAPI::class.java)
        } catch (e: Exception) {
            mCallback?.onResponse(null, Error(e))
        }
        mLessonsAPI?.getAllLessons()?.enqueue(this)
    }

    override fun onFailure(call: Call<List<Lesson>>?, t: Throwable?) {
        mCallback?.onResponse(null, Error(t))
    }

    override fun onResponse(call: Call<List<Lesson>>?, response: Response<List<Lesson>>?) {
        mCallback?.onResponse(response?.body(), ResponseHelper(response).getError())
    }

}