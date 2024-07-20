package com.example.noteappwithoutroom.domain.usecase

import com.example.noteappwithoutroom.domain.model.NoteEntity
import com.example.noteappwithoutroom.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {

    suspend fun invoke(noteEntity: NoteEntity) {
        repository.delete(noteEntity)
    }
}