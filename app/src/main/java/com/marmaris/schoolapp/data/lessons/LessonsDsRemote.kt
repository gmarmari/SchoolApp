package com.marmaris.schoolapp.data.lessons

import com.marmaris.schoolapp.retrofit.LessonsGetController
import com.marmaris.schoolapp.util.AppExecutors
import java.lang.Error

/**
 * Implementation of a LessonsDs from a remote source.
 * No remote source is implemented yet so all methods call callbacks onError.
 */
class LessonsDsRemote(
    private val mAppExecutors : AppExecutors,
    private val mBaseUrl : String) : LessonsDs {

    override fun getLessons(callback: LessonsDs.GetLessonsCallback) {
        mAppExecutors.mNetworkIO.execute{
            LessonsGetController().start(mBaseUrl, object : LessonsDs.GetLessonsCallback{

                override fun onResponse(lessons: List<Lesson>?, error: Error?) {
                    mAppExecutors.mMainThread.execute{
                        callback.onResponse(lessons, error)
                    }
                }

            })
        }
    }

    override fun getLesson(lessonId: String, callback: LessonsDs.GetLessonCallback) {

    }

    override fun deleteAllLessons(callback: LessonsDs.DeleteAllLessonsCallback?) {

    }

    override fun insertLesson(lesson: Lesson, callback: LessonsDs.InsertLessonCallback?) {

    }

}