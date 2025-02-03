package com.esrabildik.noteapp.usecases

import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.domain.repository.NoteRepository

class DeleteNote(private val repository: NoteRepository) {

    suspend operator fun invoke(note : Note){
          repository.deleteNote(note)
    }
}