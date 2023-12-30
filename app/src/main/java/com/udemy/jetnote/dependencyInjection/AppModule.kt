package com.udemy.jetnote.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.udemy.jetnote.data.NoteDatabase
import com.udemy.jetnote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This is used to add bindings to Hilt. This tells Hilt how to provide instances of different types.
 * This is used to help Room build an instance of the database which prevents Room from creating a new
 * database every time the app starts.
 *
 * Here we will add all bindings for hilt
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Function that extends the Note DAO and gives access to the DAO
     */
    @Singleton // Gets a single instance
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase) : NoteDatabaseDao
    = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase
    = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}