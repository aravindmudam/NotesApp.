package uk.ac.tees.mad.w9646370.interfaces

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9646370.Note

interface INoteRepo {
    suspend fun addNote(note: Note)
    suspend fun editNote(note: Note)
    suspend fun getNotes(): Flow<List<Note>>
}