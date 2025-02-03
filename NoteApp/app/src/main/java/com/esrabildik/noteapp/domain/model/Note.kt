package com.esrabildik.noteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey val id : Int? = null,
    val title : String,
    val teamName : String,
    val startDate : String,
    val endDate : String,
    val isCompleted : Boolean = false
)

class InvalidNoteException(message:String): Exception(message)
