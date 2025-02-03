package com.esrabildik.noteapp.presentation.homePage

import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.utils.NoteOrder
import com.esrabildik.noteapp.utils.OrderType

// Amaç, UI'ın her zaman doğru bilgiyi saklaması
// UI içerisinde state değişkeni olarak saklanıyor
// ve observe edilerek UI güncel kalmasını sağlıyor.
data class NotesState(
    val notes : List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    val isOrderSectionVisible : Boolean = false
)