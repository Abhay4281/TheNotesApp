package com.example.thenotesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.thenotesapp.model.Note
import com.example.thenotesapp.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application , private val noteRepository: NoteRepository):AndroidViewModel(app) {
    //viewmodel act as a bridge between the view and the model
    //create five function that will be launched through cororutine scope
    //coroutine is cancelled when associated viewmodel is cleared to prevent potential memory leaks


    fun addNote(note: Note)=
    viewModelScope.launch {
        noteRepository.insertNote(note)//will launch in bg using coroutines
    }
    fun deleteNote(note: Note)=
        viewModelScope.launch {
            noteRepository.deleteNote(note)//will launch in bg using coroutines
        }
    fun updateNote(note: Note)=
        viewModelScope.launch {
            noteRepository.updateNote(note)//will launch in bg using coroutines
        }
    fun getAllNotes()=noteRepository.getAllNotes()

    fun searchNote(query:String?)=noteRepository.searchNote(query)
}