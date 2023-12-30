package com.udemy.jetnote.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "notes_tbl") // Defines the Room DB table name
data class Note(

    @PrimaryKey // Sets the id as the primary key
    val id : UUID = UUID.randomUUID(), // Creates unique random ID

    @ColumnInfo(name = "note_title") // Defines column name
    val title : String,

    @ColumnInfo(name = "note_description")
    val description : String,

    @ColumnInfo(name = "note_entry_date")

    val entryDate : LocalDateTime = LocalDateTime.now()
)
