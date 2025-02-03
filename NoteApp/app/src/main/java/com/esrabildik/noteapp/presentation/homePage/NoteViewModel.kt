package com.esrabildik.noteapp.presentation.homePage

import androidx.compose.runtime.State
import androidx.compose.runtime.isTraceInProgress
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrabildik.noteapp.domain.model.Note
import com.esrabildik.noteapp.usecases.NoteUseCases
import com.esrabildik.noteapp.utils.NoteOrder
import com.esrabildik.noteapp.utils.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    // _state, not ekranının mevcut durumunu saklar.
    private val _state = mutableStateOf(NotesState())
    // state, UI tarafından gözlemlenen değişken (sadece okunur)
    val state : State<NotesState> = _state

    // recentDeleteNote, son silinen notu tutar.
    // kullanıcı, geri al derse notu geri ekler.
    private var recentDeleteNote : Note? = null


    // veri akışını (flow) yönetmek için kullanılır.
    // her çağrıda eski akışı iptal eder ve yeni bir çağrı oluşturur.
    private var getNotesJob : Job? = null


    // viewmodel ilk oluşturulduğunda, notları en yeni tarihe göre sıralar.
    init {
        getNotes(noteOrder = NoteOrder.Date(OrderType.Descending))
    }


    fun onEvent(event : NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType){
                    return
                }
                getNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentDeleteNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    // ?: operatörü ile recentDeleteNote null değilse recentDeleteNote değeri kullanılır
                    // eğer recentDeleteNote null ise, return@launch çalışır ve fonksiyondan çıkılır.
                    noteUseCases.addNote(recentDeleteNote ?: return@launch)

                    // notu ekledikten sonra, eski notu sıfırlıyoruz ki tekrar eklemesin
                    recentDeleteNote = null
                }
            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value =
                    state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }

            else -> {}

        }
    }

    private fun getNotes(noteOrder: NoteOrder){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder).onEach { notes ->
            _state.value = state.value.copy(
                notes = notes,
                noteOrder = noteOrder
            )
        }.launchIn(viewModelScope)
    }
}