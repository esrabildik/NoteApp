package com.esrabildik.noteapp.usecases

import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.domain.repository.NoteRepository
import com.esrabildik.noteapp.utils.NoteOrder
import com.esrabildik.noteapp.utils.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            //whatever the list we get from the repository, we map that to our newList
            when (noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.startDate }

                    }
                }

                is OrderType.Descending -> {
                    when (noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.startDate }
                    }
                }
            }
        }
    }
}

