package uk.ac.tees.mad.w9646370

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var title : String,
    var description : String = "",
    var isDeleted : Boolean = false,
    var isBookMarked : Boolean = false
)