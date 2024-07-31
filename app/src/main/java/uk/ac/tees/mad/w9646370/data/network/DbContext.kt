package uk.ac.tees.mad.w9646370.network

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.ac.tees.mad.w9646370.Note
import uk.ac.tees.mad.w9646370.dao.IDao

@Database(entities = [Note::class], version = 1)
abstract class DbContext: RoomDatabase() {
    abstract val dao: IDao
}