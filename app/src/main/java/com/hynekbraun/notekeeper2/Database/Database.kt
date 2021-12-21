package com.hynekbraun.notekeeper2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hynekbraun.notekeeper2.Util.NoteConverter
import com.hynekbraun.notekeeper2.Util.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(NoteConverter::class)

abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDAO(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "item_database"
                ).fallbackToDestructiveMigration()
                    .build()


                INSTANCE = instance
                return instance
            }
        }
    }

}