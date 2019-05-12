package com.marmaris.schoolapp.data.lessons

import java.lang.Error

open class LessonsRepo private constructor(
    val mLessonsRemoteSource : LessonsDs,
    val mLessonsLocalSource : LessonsDs
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
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

    //endregion Construction


    //region LessonsDs

    override fun getLessons(callback: LessonsDs.GetLessonsCallback) {
        getLessonsFromRemoteDataSource(object :
            LessonsDs.GetLessonsCallback {

            override fun onSuccess(lessons: List<Lesson>) {
                callback.onSuccess(lessons)
                refreshLocalLessons(lessons)
            }

            override fun onError(error: Error) {
                getLessonsFromLocalDataSource(callback)
            }

        })
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

    private fun getLessonsFromRemoteDataSource(callback: LessonsDs.GetLessonsCallback) {
        mLessonsRemoteSource.getLessons(callback)
    }

    private fun getLessonsFromLocalDataSource(callback: LessonsDs.GetLessonsCallback) {
        mLessonsLocalSource.getLessons(callback)
    }

    /**
     * Deletes the saved lessons and inserts the given lessons
     */
    private fun refreshLocalLessons(lessons: List<Lesson>) {
        mLessonsLocalSource.deleteAllLessons(null)
        for (lesson in lessons) {
            mLessonsLocalSource.insertLesson(lesson, null)
        }
    }

}