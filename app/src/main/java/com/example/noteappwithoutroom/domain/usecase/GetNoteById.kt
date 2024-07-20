package com.example.noteappwithoutroom.domain.usecase

import com.example.noteappwithoutroom.domain.model.NoteEntity
import com.example.noteappwithoutroom.domain.repository.NoteRepository

class GetNoteById(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long): NoteEntity? {
        return repository.getNoteById(id)
    }
}