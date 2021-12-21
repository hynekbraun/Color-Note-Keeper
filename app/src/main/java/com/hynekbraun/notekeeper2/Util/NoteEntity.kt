package com.hynekbraun.notekeeper2.Util

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes_table")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val header: String,
    val description: String,
    val date: Date,
    val color: Int,
    val isDone: Boolean
)
