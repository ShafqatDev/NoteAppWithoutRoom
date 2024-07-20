package com.example.noteappwithoutroom.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteappwithoutroom.presentation.add_note.AddNoteScreen
import com.example.noteappwithoutroom.presentation.components.Routes
import com.example.noteappwithoutroom.presentation.note.NoteScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.NoteList.name) {
        composable(Routes.NoteList.name) {
            NoteScreen()
        }
        composable("${Routes.AddNote.name}/{noteId}") {
            val noteId = it.arguments?.getString("noteId")
            noteId?.let { id ->
                AddNoteScreen(noteId = id.toLong())
            }
        }
    }
}