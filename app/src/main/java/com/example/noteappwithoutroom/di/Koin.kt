package com.example.noteappwithoutroom.di

import com.example.noteappwithoutroom.data.communicator.Communicator
import com.example.noteappwithoutroom.data.repository.NoteRepositoryImp
import com.example.noteappwithoutroom.domain.repository.NoteRepository
import com.example.noteappwithoutroom.domain.usecase.AddNoteUseCase
import com.example.noteappwithoutroom.domain.usecase.DeleteNoteUseCase
import com.example.noteappwithoutroom.domain.usecase.GetNoteById
import com.example.noteappwithoutroom.domain.usecase.GetNotesUseCase
import com.example.noteappwithoutroom.domain.usecase.UpdateNoteUseCase
import com.example.noteappwithoutroom.presentation.add_note.AddNoteViewModel
import com.example.noteappwithoutroom.presentation.note.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleList = module {
    single {
        //Make Communicator Singleton
        Communicator()
    }
    viewModel {
        NoteViewModel(get(), get())
    }
    viewModel {
        AddNoteViewModel(get(), get(), get())
    }
    factory<NoteRepository> {
        NoteRepositoryImp(get())
    }
    factory {
        AddNoteUseCase(get())
    }
    factory {
        DeleteNoteUseCase(get())
    }
    factory {
        GetNotesUseCase(get())
    }
    factory {
        GetNoteById(get())
    }
    factory {
        UpdateNoteUseCase(get())
    }
}