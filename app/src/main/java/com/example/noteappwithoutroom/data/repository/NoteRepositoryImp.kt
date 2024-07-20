package com.example.noteappwithoutroom.data.repository

import com.example.noteappwithoutroom.data.communicator.Communicator
import com.example.noteappwithoutroom.domain.model.NoteEntity
import com.example.noteappwithoutroom.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImp(
    private val communicator: Communicator
) : NoteRepository {
    override suspend fun insert(title: String, description: String, image: String) {
        communicator.addText(title, description, image)
    }

    override suspend fun update(id: Long, title: String, description: String, image: String) {
        communicator.updateText(id, title, description, image)
    }

    override suspend fun delete(noteEntity: NoteEntity) {
        communicator.remove(noteEntity)
    }

    override suspend fun getNoteById(id: Long): NoteEntity? {
        return communicator.getNoteById(id)
    }

    override fun getNotes(): Flow<List<NoteEntity>> {
        return communicator.noteList.map {
            it.itemsList
        }
    }
}