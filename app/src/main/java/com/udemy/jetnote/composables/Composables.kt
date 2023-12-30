package com.udemy.jetnote.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udemy.jetnote.models.Note
import java.time.format.DateTimeFormatter

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text : String,
    label : String,
    maxLine : Int = 1,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
){
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(MaterialTheme.colorScheme.onSecondary),
        maxLines = maxLine,
        label = { Text(
            text = label,
            modifier = Modifier.padding(2.dp),

            )},
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions (
            onDone = {
                onImeAction()
                keyboardController?.hide()
            }
        ),
        modifier = modifier.padding(10.dp)
    )
}

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    shape: Shape,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
){
    Button(
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun NoteCard(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit

){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxSize()
            //.height(150.dp)
            .clickable {
                onNoteClicked(note)

            },
        shape = MaterialTheme.shapes.small,
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        )

    ) {
        // Sets the Card Content Composable in the card composable
        NoteCardContent(note)

    }
}

@Composable
fun NoteCardContent(note: Note){

    Column (
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            ){
                append("Title: ")
            }
            withStyle(style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 13.sp
            )
            ){
                append(note.title)
            }
        }, modifier = Modifier.padding(5.dp))

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            ){
                append("Description: ")
            }
            withStyle(style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 13.sp
            )
            ){
                append(note.description)
            }
        }, modifier = Modifier.padding(5.dp))

        Text(buildAnnotatedString {
            withStyle(style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            ){
                append("Date: ")
            }
            withStyle(style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontSize = 13.sp
            )
            ){
                // appends with a formatted date
                append(note.entryDate.format(DateTimeFormatter.ofPattern("EEE, MMM d")).toString())
            }
        }, modifier = Modifier.padding(5.dp))
    }
}