package com.example.noteappwithoutroom.domain.usecase

import com.example.noteappwithoutroom.domain.model.NoteEntity
import com.example.noteappwithoutroom.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(): Flow<List<NoteEntity>> {
        return repository.getNotes()
    }
}