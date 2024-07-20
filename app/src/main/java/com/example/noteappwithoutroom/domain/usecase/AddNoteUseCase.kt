package com.example.noteappwithoutroom.domain.usecase

import com.example.noteappwithoutroom.domain.model.NoteEntity
import com.example.noteappwithoutroom.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(title: String, description: String,image:String) {
        repository.insert(title, description, image = image)
    }
}