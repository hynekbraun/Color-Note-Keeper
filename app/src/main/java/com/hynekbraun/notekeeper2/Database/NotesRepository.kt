package com.hynekbraun.notekeeper2.Database

import androidx.lifecycle.LiveData
import com.hynekbraun.notekeeper2.Util.NoteEntity

class NotesRepository(private val notesDao: NotesDao) {


    val readAllNotes: LiveData<List<NoteEntity>>  = notesDao.readAllNotes()


    suspend fun addNote(note: NoteEntity) {
        notesDao.addNote(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        notesDao.deleteNote(note)
    }

    suspend fun deleteAllNotes() {
        notesDao.deleteAllNotes()
    }

    suspend fun updateNote(note: NoteEntity) {
        notesDao.updateNote(note)
    }

    fun searchNote(searchQuery: String): LiveData<List<NoteEntity>> {
        return notesDao.searchNote(searchQuery)
    }

}