package com.marmaris.schoolapp.data.lessons

import java.lang.Error

/**
 * Implementation of a LessonsDs from a remote source.
 * No remote source is implemented yet so all methods call callbacks onError.
 */
class LessonsDsRemote : LessonsDs {

    private val mLessons =  ArrayList<Lesson>()

    override fun getLessons(callback: LessonsDs.GetLessonsCallback) {
        mLessons.clear()
        addLesson("Maths")
        addLesson("Physics")
        addLesson("Greek")
        addLesson("English")
        addLesson("German")
        addLesson("Sport")
        callback.onSuccess(mLessons)
    }

    override fun getLesson(lessonId: String, callback: LessonsDs.GetLessonCallback) {
        val mStream = mLessons.stream().filter { les -> les.mId == lessonId}.findFirst()
        if (mStream?.get() != null)
            callback.onSuccess(mStream.get())
        else
            callback.onError(Error("No remote data source"))
    }

    override fun deleteAllLessons(callback: LessonsDs.DeleteAllLessonsCallback?) {
        mLessons.clear()
        callback?.onCompleted()
    }

    override fun insertLesson(lesson: Lesson, callback: LessonsDs.InsertLessonCallback?) {
        mLessons.add(lesson)
        callback?.onCompleted()
    }

    private fun addLesson(title: String) {
        val lesson = Lesson()
        lesson.mTitle = title
        mLessons.add(lesson)
    }

}