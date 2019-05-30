package com.marmaris.schoolapp.data.lessons

import java.lang.Error

open class LessonsRepo private constructor(
    private val mRemoteSource : LessonsDs?,
    private val mLocalSource : LessonsDs
) : LessonsDs {

    //region Construction

    companion object {

        private var INSTANCE: LessonsRepo? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         * @param lessonsRemoteSource the backend data source
         * @param lessonsLocalSource  the device storage data source
         * @return the [LessonsRepo] instance
         */
        @JvmStatic
        fun getInstance(lessonsRemoteSource : LessonsDsRemote, lessonsLocalSource : LessonsDsLocal) =
            INSTANCE ?: synchronized(LessonsRepo::class.java) {
                INSTANCE ?: LessonsRepo(lessonsRemoteSource, lessonsLocalSource)
                    .also {
                        INSTANCE = it
                    }
            }

        /**
         * Returns the single instance of this class, creating it if necessary.
         * @param lessonsLocalSource  the device storage data source
         * @return the [LessonsRepo] instance
         */
        @JvmStatic
        fun getInstance(lessonsLocalSource : LessonsDsLocal) =
            INSTANCE ?: synchronized(LessonsRepo::class.java) {
                INSTANCE ?: LessonsRepo(null, lessonsLocalSource)
                    .also {
                        INSTANCE = it
                    }
            }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @Suppress("unused")
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    //endregion Construction


    //region LessonsDs

    /***
     * If remote is on:
     * Get lessons from remote source.
     * If no error then refresh the local saved lessons.
     * If an error occurs while getting lessons from remote source, get the lessons from local source.
     * If remote is off:
     * Get lessons from local source
     * @param callback : a LessonsDs.GetLessonsCallback
     */
    override fun getLessons(callback: LessonsDs.GetLessonsCallback) {
        if (mRemoteSource != null) {
            mRemoteSource.getLessons(object :  LessonsDs.GetLessonsCallback {

                override fun onResponse(lessons: List<Lesson>?, error: Error?) {

                    if (lessons != null) {
                        refreshLocalLessons(lessons)
                        callback.onResponse(lessons, error)
                    } else {
                        getLessonsFromLocalSource(callback, error)
                    }

                }

            })
        } else {
            getLessonsFromLocalSource(callback, null)
        }
    }

    override fun getLesson(lessonId: String, callback: LessonsDs.GetLessonCallback) {
        throw NotImplementedError("getLesson is not implemented for this class")
    }

    override fun deleteAllLessons(callback: LessonsDs.DeleteAllLessonsCallback?) {
        throw NotImplementedError("deleteAllLessons is not implemented for this class")
    }

    override fun insertLesson(lesson: Lesson, callback: LessonsDs.InsertLessonCallback?) {
        throw NotImplementedError("insertLesson is not implemented for this class")
    }

    //endregion LessonsDs


    /**
     * Deletes the saved lessons and inserts the given lessons
     * @param lessons : a list with lessons
     */
    private fun refreshLocalLessons(lessons: List<Lesson>) {
        mLocalSource.deleteAllLessons(null)
        for (lesson in lessons) {
            mLocalSource.insertLesson(lesson, null)
        }
    }

    /**
     * Gets the lessons from the local source
     * @param callback : a LessonsDs.GetLessonsCallback
     * @param remoteError : the error while getting the lessons from remote source
     **/
    private fun getLessonsFromLocalSource(callback: LessonsDs.GetLessonsCallback, remoteError: Error?) {
        mLocalSource.getLessons(object : LessonsDs.GetLessonsCallback{

            override fun onResponse(lessons: List<Lesson>?, error: Error?) {
                callback.onResponse(lessons, remoteError ?: error)
            }

        })
    }

}