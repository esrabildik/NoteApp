package com.esrabildik.noteapp.presentation.homePage

import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.utils.NoteOrder

//Kullanıcı tarafından yapılan tüm işlemleri temsil eder.
sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    object RestoreNote : NotesEvent()
    object ToggleOrderSection : NotesEvent()
}