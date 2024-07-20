package com.example.noteappwithoutroom.presentation.add_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappwithoutroom.domain.repository.NoteRepository
import com.example.noteappwithoutroom.domain.usecase.AddNoteUseCase
import com.example.noteappwithoutroom.domain.usecase.GetNoteById
import com.example.noteappwithoutroom.domain.usecase.UpdateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddNoteState(
    val id: Long = -1,
    val image: String = "",
    val title: String = "",
    val description: String = "",
)

class AddNoteViewModel(
    private val addNote: AddNoteUseCase,
    private val getNoteById: GetNoteById,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AddNoteState())
    val state = _state.asStateFlow()

    fun loadNoteById(noteId: Long) {
        viewModelScope.launch {
            val note = getNoteById.invoke(noteId)
            note?.let {
                _state.update { currentState ->
                    currentState.copy(
                        id = note.id,
                        title = note.title,
                        description = note.description,
                        image = note.image
                    )
                }
            }
        }
    }

    fun onTitleChange(title: String) {
        _state.update { currentState ->
            currentState.copy(
                title = title
            )
        }
    }

    fun onDescriptionChange(description: String) {
        _state.update { currentState ->
            currentState.copy(
                description = description
            )
        }
    }

    private fun saveData() {
        viewModelScope.launch {
            addNote.invoke(
                title = state.value.title,
                description = state.value.description,
                image = state.value.image
            )
        }
    }

    private fun updateData() {
        viewModelScope.launch {
            updateNoteUseCase.invoke(
                id = state.value.id,
                title = state.value.title,
                description = state.value.description,
                image = state.value.image
            )
        }
    }

    fun saveOrUpdate() {
        if (state.value.id == -1L) {
            saveData()
        } else {
            updateData()
        }
    }

    fun onImageChange(image: String) {
        _state.update { currentState ->
            currentState.copy(
                image = image
            )
        }
    }
}
