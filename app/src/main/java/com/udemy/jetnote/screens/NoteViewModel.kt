package com.udemy.jetnote.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemy.jetnote.models.Note
import com.udemy.jetnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    // Connects with note repository
    private val repository: NoteRepository

): ViewModel() {


    //private var noteList = mutableStateListOf<Note>()

    // Create a private note list
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    // adds data when class is initialized
    init {
        //noteList.addAll(NotesDataSource().loadNotes())

        // Dispatcher allows processes to run in parallel
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllNotes().distinctUntilChanged()
                .collect{
                listOfNotes ->
                if (listOfNotes.isNullOrEmpty()){
                    Log.d("NoteViewModel", "Note List is Empty")
                }
                else {
                    _noteList.value = listOfNotes
                }
            }
        }

    }

    fun insertNote(note: Note) = viewModelScope.launch { repository.insertNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }


}