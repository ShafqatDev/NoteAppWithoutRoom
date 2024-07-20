package com.example.noteappwithoutroom.presentation.note

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.noteappwithoutroom.myapp.LocalNavController
import com.example.noteappwithoutroom.presentation.components.Routes
import com.example.noteappwithoutroom.presentation.note.components.NoteCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: NoteViewModel = koinViewModel()
) {
    val navController = LocalNavController.current
    val state = viewModel.state.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Notes",style = MaterialTheme.typography.titleLarge) },actions = {
                IconButton(onClick = {
                    viewModel.onShuffle()
                }) {
                    Icon(imageVector = Icons.Default.Shuffle, contentDescription = "Shuffle")
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.AddNote.name + "/${-1}") }) {
                Image(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(state.itemsList) { note ->
                NoteCard(
                    note = note,
                    onDelete = {
                        viewModel.onDelete(note)
                    },
                    onItemClick = {
                        navController.navigate(Routes.AddNote.name + "/${note.id}")
                    }
                )
            }
        }
    }
}