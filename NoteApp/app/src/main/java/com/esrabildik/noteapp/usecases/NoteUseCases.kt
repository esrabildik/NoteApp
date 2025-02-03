package com.esrabildik.noteapp.usecases

data class NoteUseCases (
    val addNote : AddNote,
    val deleteNote : DeleteNote,
    val getNotes  :GetNotes,
    val getNoteID : GetNoteById
    )