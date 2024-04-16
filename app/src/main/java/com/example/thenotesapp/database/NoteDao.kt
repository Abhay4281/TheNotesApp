package com.example.thenotesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.thenotesapp.model.Note


@Dao
interface NoteDao {

    //notedao interface used to write wuery like insert read update delete
//suspend indicates ki function is written from coroutine
//onconflict means ki age pehle se he same data h to ye usse replace krdega
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note:Note)

    @Delete
    suspend fun deleteNote(note:Note)
//query to display all the notes recently created will be at the top previosuly at bottom
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes():LiveData<List<Note>>
//if keyword matches witth title or desc then store it in livedata containing notes can be null if no specific criteria provided
    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE:query OR noteDesc LIKE :query")
    fun searchNote(query:String?):LiveData<List<Note>>

}