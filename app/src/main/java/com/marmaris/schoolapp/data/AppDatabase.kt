package com.marmaris.schoolapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marmaris.schoolapp.data.lessons.Lesson
import com.marmaris.schoolapp.data.lessons.LessonsDao

/**
 * The Room Database for the App
 */
@Database(entities = [Lesson::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun lessonDao(): LessonsDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        private val lock = Any()

        /**
         * if (INSTANCE == null) creates a new instance
         * @return an instance of AppDatabase
         */
        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "AppDatabase.db")
                        .build()
                }
                return INSTANCE!!
            }
        }


        /**
         * deletes the saved instance
         */
        @Suppress("unused")
        fun destroyInstance() {
            INSTANCE = null
        }

    }

}