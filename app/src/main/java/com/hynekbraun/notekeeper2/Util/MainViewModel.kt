package com.hynekbraun.notekeeper2.Util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hynekbraun.notekeeper2.Database.NotesDatabase
import com.hynekbraun.notekeeper2.Database.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    val allNotes: LiveData<List<NoteEntity>>
    private val repository: NotesRepository

    init{
        val notesDao = NotesDatabase.getDatabase(application).notesDAO()
        repository = NotesRepository(notesDao)
        allNotes = repository.readAllNotes
    }
    fun addNote(note: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }
    fun deleteNote(note: NoteEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
    fun updateNote(note: NoteEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }
    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllNotes()
        }
    }
    fun searchNote(searchQuery: String): LiveData<List<NoteEntity>> {
        return repository.searchNote(searchQuery)
    }

}