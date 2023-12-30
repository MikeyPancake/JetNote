package com.udemy.jetnote.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.udemy.jetnote.models.Note
import kotlinx.coroutines.flow.Flow

/**
 * The Dao is responsible for all Room DB interactions (Reads, Writes, ect.)
 *
 * In order to interact with DB, an instance of the DAO must be created
 *
 * Suspend function is used in Coroutines which allows a function to be suspended and used in a different
 *  thread. This prevents the UI from stopping when an action is being conducted
 */
@Dao // Annotates that this is a DAO
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl") // Query of all notes in the note table
    fun getNotes() : Flow<List<Note>> // handles the flow state

    @Query("SELECT * from notes_tbl where id = :id")
    suspend fun getNoteById(id: String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE) // If there are errors inserting a note, replace it with new note
    suspend fun insertNote(note :Note)

    @Update(onConflict = OnConflictStrategy.REPLACE) // If there are errors updating a note, replace it with new note
    suspend fun updateNote(note : Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAllNotes()

    @Delete
    suspend fun deleteNote(note : Note)

}
