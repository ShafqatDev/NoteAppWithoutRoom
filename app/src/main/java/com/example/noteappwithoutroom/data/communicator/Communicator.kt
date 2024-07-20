package com.example.noteappwithoutroom.data.communicator

import com.example.noteappwithoutroom.domain.model.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CommunicatorState(
    val itemsList: List<NoteEntity> = emptyList()
)

class Communicator {
    private val _noteList = MutableStateFlow(CommunicatorState())
    val noteList = _noteList.asStateFlow()

    fun addText(title: String, description: String,image:String) {
        val list = noteList.value.itemsList.toMutableList()
        list.add(NoteEntity(title = title, description = description, image =image ))
        _noteList.update {
            it.copy(itemsList = list)
        }
    }

    fun updateText(id: Long, title: String, description: String,image:String) {
        val list = noteList.value.itemsList.toMutableList()
        val index = list.indexOfFirst { it.id == id }
        if (index != -1) {
            list[index] = NoteEntity(id = id, title = title, description = description,image =image)
            _noteList.update {
                it.copy(itemsList = list)
            }
        }
    }

    fun remove(noteEntity: NoteEntity) {
        val list = _noteList.value.itemsList.toMutableList()
        list.remove(noteEntity)
        _noteList.update {
            it.copy(itemsList = list)
        }
    }

    fun getNoteById(id: Long): NoteEntity? {
        return _noteList.value.itemsList.find { it.id == id }
    }
}
