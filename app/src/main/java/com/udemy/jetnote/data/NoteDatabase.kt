package com.udemy.jetnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.udemy.jetnote.models.Note
import com.udemy.jetnote.utils.DateConverter
import com.udemy.jetnote.utils.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class) // tells room how to convert local Date Time
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}