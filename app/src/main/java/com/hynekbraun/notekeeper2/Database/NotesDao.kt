package com.hynekbraun.notekeeper2.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hynekbraun.notekeeper2.Util.NoteEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(item: NoteEntity)

    @Update
    suspend fun updateNote(item: NoteEntity)

    @Delete
    suspend fun deleteNote(item: NoteEntity)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM notes_table ORDER BY date DESC")
    fun readAllNotes(): LiveData<List<NoteEntity>>

//    @Query("SELECT * FROM notes_table WHERE header LIKE :searchQuery")
//    fun searchNote(searchQuery: String): LiveData<List<NoteEntity>>

}