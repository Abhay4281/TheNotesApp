package com.example.thenotesapp.repository

import androidx.room.Query
import com.example.thenotesapp.database.NoteDatabase
import com.example.thenotesapp.model.Note


//repository place or container where something is stored
class NoteRepository (private val db:NoteDatabase){

    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun  deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updateNote(note: Note) = db.getNoteDao().updateNote(note)

    fun getAllNotes()=db.getNoteDao().getAllNotes()

    fun searchNote(query: String?)=db.getNoteDao().searchNote(query)


/*suspend makes sure ki everything is running on a bg thread
* insertNote is present in notedao that is present in notedb
* insertNote->NoteDao->Notedb
* (note) is the passed argument which needs to be saved
* same with delete note and update note
* getallnotes doesnt require any parameter because it is retriving
* searchnotes require parameter to search notesearches note acc to query */

}