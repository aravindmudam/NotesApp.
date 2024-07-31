package uk.ac.tees.mad.w9646370.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9646370.Note

@Dao
interface IDao {
    @Query("SELECT * FROM Note where isDeleted = 0")
     fun getAllNotes(): Flow<List<Note>>

    @Insert
     fun addNotes(note: Note)

    @Update
     fun editNote(note: Note)
}