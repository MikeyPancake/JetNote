package com.udemy.jetnote.repository

import com.udemy.jetnote.data.NoteDatabaseDao
import com.udemy.jetnote.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * This is the "Repository" used to interact with the Room Database
 * This class is used by the view models to access the DAO and in turn interacts with the DB.
 * This ensures that all actions are detached and not coupled
 */
class NoteRepository @Inject constructor(
    // gives access to the DAO
    private val noteDatabaseDao: NoteDatabaseDao
) {

    // Here we access our DAO function to insert, update, and delete a note
    suspend fun insertNote(note: Note) = noteDatabaseDao.insertNote(note)

    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)

    suspend fun deleteAllNotes(note: Note) = noteDatabaseDao.deleteAllNotes()

    fun getAllNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

}