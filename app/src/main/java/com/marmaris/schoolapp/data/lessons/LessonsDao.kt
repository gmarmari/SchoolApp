package com.marmaris.schoolapp.data.lessons

import androidx.room.*

/**
 * Data Access Object for the Lesson table.
 */
@Suppress("unused")
@Dao
interface LessonsDao {

    /**
     * Select all lessons from the Lesson table.
     * @return all lessons.
     */
    @Query("SELECT * FROM Lesson") fun getLessons(): List<Lesson>

    /**
     * Select a lesson by id.
     * @param lessonId the id of the lesson.
     * @return the lesson with the given lessonId.
     */
    @Query("SELECT * FROM Lesson WHERE id = :lessonId") fun getLessonById(lessonId : String): Lesson?

    /**
     * Insert a lesson in the database. If the lesson already exists, replace it.
     * @param lesson the lesson to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertLesson(lesson: Lesson)

    /**
     * Update a lesson.
     * @param lesson the lesson to be updated
     * @return the number of lessons updated. This should always be 1.
     */
    @Update
    fun updateLesson(lesson: Lesson): Int

    /**
     * Delete a lesson by id.
     * @return the number of lessons deleted. This should always be 1.
     */
    @Query("DELETE FROM Lesson WHERE id = :lessonId") fun deleteTaskById(lessonId: String): Int

    /**
     * Delete all lessons.
     */
    @Query("DELETE FROM Lesson") fun deleteAllLessons()

}