package com.udemy.jetnote.screens

import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.udemy.jetnote.R
import com.udemy.jetnote.composables.FilledButton
import com.udemy.jetnote.composables.NoteCard
import com.udemy.jetnote.composables.NoteInputText
import com.udemy.jetnote.data.NotesDataSource
import com.udemy.jetnote.models.Note
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen (

    notes: List<Note>,
    onAddNote : (Note) -> Unit, // pass note object and passes nothing
    onRemoveNote : (Note) -> Unit

) {

    // State holder for the text box
    var title by remember {
        mutableStateOf("")
    }

    var description by remember{
        mutableStateOf("")
    }
    // creates the context for Toast
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopAppBar(R.string.app_name)

        // Note Content
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Note title input text box
            NoteInputText(
                text = title,
                label = "Title",
                onTextChange = {
                    // Validates if text has been added
                    if(it.all {
                        char ->
                        char.isLetter() || char.isWhitespace()
                        })
                        title = it
                }
            )
            // Note Description input text
            NoteInputText(
                text = description,
                label = "Note Content",
                onTextChange = {
                    // Validates if text has been added
                    if(it.all {
                        char ->
                        char.isLetter() || char.isWhitespace()
                    })
                        description = it
                }
            )
            // Save Button
            FilledButton(
                shape = CircleShape,
                text = "Save",
                onClick = {
                    // If there is text in the edit text box
                    if (title.isNotEmpty() && description.isNotEmpty()){

                        onAddNote(Note(id = UUID.randomUUID(),title, description))
                        // Clears fields once save is clicked
                        title = ""
                        description = ""

                        Log.d("Note Screen", " Note added success title: $title description: $description ")

                        // Toast message
                        Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()
                    }

                }
            )

        }
        // Adds divider
        Divider(modifier = Modifier.padding(10.dp))

        LazyColumn {
            items (notes){
                note ->
                NoteCard(note = note, onNoteClicked = { onRemoveNote(note)})
            }

        }

    }

}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(resourceId: Int) {
    androidx.compose.material3.TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        title = {
            Text(text = stringResource(id = resourceId))
        },
        actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    )
}


@Preview (showBackground = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}