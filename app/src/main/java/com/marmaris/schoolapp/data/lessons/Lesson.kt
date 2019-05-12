package com.marmaris.schoolapp.data.lessons

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Lesson {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var mId : String = UUID.randomUUID().toString()

    @ColumnInfo(name = "title")
    var mTitle : String = ""
}