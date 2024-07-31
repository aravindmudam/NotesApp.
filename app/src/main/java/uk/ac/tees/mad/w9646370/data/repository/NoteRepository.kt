package uk.ac.tees.mad.w9646370.repository

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import uk.ac.tees.mad.w9646370.Note
import uk.ac.tees.mad.w9646370.dao.IDao
import uk.ac.tees.mad.w9646370.interfaces.INoteRepo

class NoteRepository(private val dao: IDao) : INoteRepo {
    override suspend fun addNote(note: Note) {
        withContext(IO){
            dao.addNotes(note)
        }
    }

    override suspend fun editNote(note: Note) {
        withContext(IO){
            dao.editNote(note)
        }
    }

    override suspend fun getNotes(): Flow<List<Note>> {
        return withContext(IO){
            dao.getAllNotes()
        }
    }
}