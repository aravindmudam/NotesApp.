package uk.ac.tees.mad.w9646370

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uk.ac.tees.mad.w9646370.Note
import uk.ac.tees.mad.w9646370.interfaces.INoteRepo
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: INoteRepo,
) : ViewModel() {
    var noteLoading = mutableStateOf(false)
    private val _notes = MutableStateFlow(emptyList<Note>())
    val note = _notes.asStateFlow()

    val addNoteDialogBoxToggle = mutableStateOf(false)
    val editNoteDialogBoxToggle = mutableStateOf(false)
    init {
        noteLoading.value = true
        getNotes()
        noteLoading.value = false
    }

    fun getNotes() {
        viewModelScope.launch(IO) {
            repository.getNotes().collectLatest {
                _notes.tryEmit(it)
            }
        }
    }

    fun editNote(note: Note){
        viewModelScope.launch(IO) {
            repository.editNote(note)
            getNotes()
        }
    }

    fun addNote(note: Note){
        viewModelScope.launch(IO) {
            repository.addNote(note)
            getNotes()
        }
    }

    fun deleteNode(note: Note){
        viewModelScope.launch(IO) {
            note.isDeleted = true
            repository.editNote(note)
            getNotes()
        }
    }

    fun bookmarkNode(note: Note){
        viewModelScope.launch(IO) {
            note.isBookMarked = !note.isBookMarked
            repository.editNote(note)
            getNotes()
        }
    }


}