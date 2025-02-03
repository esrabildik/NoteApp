package com.esrabildik.noteapp.usecases

import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.domain.repository.NoteRepository

class GetNoteById(private val repository : NoteRepository) {

    suspend operator fun invoke(id : Int) : Note?{
        return repository.getNoteById(id)
    }
}