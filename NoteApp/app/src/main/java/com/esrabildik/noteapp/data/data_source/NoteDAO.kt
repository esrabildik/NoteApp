package com.esrabildik.noteapp.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.esrabildik.noteapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Delete
    suspend fun deleteNote(note:Note)

    @Query("select * from note")
    fun getNotes() : Flow<List<Note>>

    @Query("select * from note where id = :id")
    fun getByIdNotes(id : Int) : Note?
}