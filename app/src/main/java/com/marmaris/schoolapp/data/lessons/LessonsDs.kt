package com.marmaris.schoolapp.data.lessons

import java.lang.Error

/**
 * The DataSource for the Lessons
 **/
interface LessonsDs {

    interface GetLessonsCallback {
        fun onSuccess(lessons: List<Lesson>)
        fun onError(error: Error)
    }
    fun getLessons(callback: GetLessonsCallback)

    interface GetLessonCallback {
        fun onSuccess(tasks: Lesson)
        fun onError(error: Error)
    }
    fun getLesson(lessonId : String, callback: GetLessonCallback)


    interface DeleteAllLessonsCallback {
        fun onCompleted()
    }
    fun deleteAllLessons(callback: DeleteAllLessonsCallback?)

    interface InsertLessonCallback {
        fun onCompleted()
    }
    fun insertLesson(lesson: Lesson, callback: InsertLessonCallback?)

}