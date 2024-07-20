package com.example.noteappwithoutroom.domain.repository

import com.example.noteappwithoutroom.domain.model.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insert(title: String, description: String,image:String)
    suspend fun update(id: Long, title: String, description: String,image:String)
    suspend fun delete(noteEntity: NoteEntity)
    suspend fun getNoteById(id: Long): NoteEntity?
    fun getNotes(): Flow<List<NoteEntity>>
}