package com.esrabildik.noteapp.usecases

import com.esrabildik.noteapp.domain.model.InvalidNoteException
import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(val repository : NoteRepository) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note){
        if (note.title.isBlank()){
            throw InvalidNoteException("Title of the note can't be empty.")
        }
        repository.insertNote(note)
    }

}