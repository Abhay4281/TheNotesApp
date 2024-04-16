package com.example.thenotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thenotesapp.model.Note
import java.util.concurrent.locks.Lock


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {


    abstract fun getNoteDao():NoteDao

//whatver present in companion is static and can be accessed anywhere by calling it

    companion object{
        @Volatile
        private var instance:NoteDatabase?=null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance?:
            createDatabase(context).also{
                instance= it
            }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                name = "note_db"
            ).build()

        /*volatile annotation enusres that changes made by one thread are visible by another thread
        * instance variable holds singleton variable notedatabase
        * lock is used to synchronise db creation
        * lock ensure ki ek br mei ek he thread jo h execute horhi h
        * invoke allows to create instance of db by calling db context
        * invoke follows singleton where it creates only one instance
        *if instance?: this check ki instance is already initialised if not it enters the synchronise block using lock
        * inside it checks ki intance is still null it createdb using createdatabase(context)
        * invoke is used to create the simplicity when creating an instance
        * double check lock property to ensure thread safety
        * fun createdatabase is responsible in creatng notedb instance using dastabase builder method
         */

    }
}