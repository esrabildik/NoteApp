package com.esrabildik.noteapp.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esrabildik.noteapp.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDAO

    companion object{
        const val DB_NAME = "noteDB"
    }
}