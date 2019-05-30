package com.marmaris.schoolapp.data.lessons

import androidx.annotation.VisibleForTesting
import com.marmaris.schoolapp.util.AppExecutors
import java.lang.Error

/**
 * Implementation of a LessonsDs from a local db.
 */
class LessonsDsLocal private constructor(
    private val mAppExecutors : AppExecutors,
    private val mLessonsDao : LessonsDao
) : LessonsDs {

    //region Construction

    companion object {
        private var INSTANCE: LessonsDsLocal? = null

        @JvmStatic
        fun getInstance(appExecutors: AppExecutors, lessonsDao: LessonsDao): LessonsDsLocal {
            if (INSTANCE == null) {
                synchronized(LessonsDsLocal::javaClass) {
                    INSTANCE =
                        LessonsDsLocal(appExecutors, lessonsDao)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }

    //endregion Construction


    //region LessonsDs

    override fun getLessons(callback: LessonsDs.GetLessonsCallback) {
        mAppExecutors.mDiskIO.execute{
            val lessons = mLessonsDao.getLessons()
            mAppExecutors.mMainThread.execute{
                if (lessons.isNotEmpty())
                    callback.onResponse(lessons, null)
                else
                    callback.onResponse(null, Error("No lessons available"))
            }
        }
    }

    override fun getLesson(lessonId: String, callback: LessonsDs.GetLessonCallback) {
        mAppExecutors.mDiskIO.execute{
            val lesson = mLessonsDao.getLessonById(lessonId)
            mAppExecutors.mMainThread.execute{
                if (lesson != null)
                    callback.onSuccess(lesson)
                else
                    callback.onError(Error("No lessons found for the id: $lessonId"))
            }
        }
    }

    override fun deleteAllLessons(callback: LessonsDs.DeleteAllLessonsCallback?) {
        mAppExecutors.mDiskIO.execute{
            mLessonsDao.deleteAllLessons()
            mAppExecutors.mMainThread.execute{
                callback?.onCompleted()
            }
        }
    }

    override fun insertLesson(lesson: Lesson, callback: LessonsDs.InsertLessonCallback?) {
        mAppExecutors.mDiskIO.execute{
            mLessonsDao.insertLesson(lesson)
            mAppExecutors.mMainThread.execute{
                callback?.onCompleted()
            }
        }
    }

    //endregion LessonsDs

}