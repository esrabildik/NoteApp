package com.esrabildik.noteapp.utils

// sıralama kriterlerini tanımlıyor.
sealed class NoteOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : NoteOrder(orderType)
    class Date(orderType: OrderType) : NoteOrder(orderType)

    // bu fonksiyon mevcut sıralama kriterini korurken, sıralama türünü değiştirmeyi sağlıyor.
    fun copyNoteOrderHelper(orderType: OrderType) :NoteOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }

    }
}