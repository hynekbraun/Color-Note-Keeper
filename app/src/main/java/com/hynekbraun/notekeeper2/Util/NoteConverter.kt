package com.hynekbraun.notekeeper2.Util

import androidx.room.TypeConverter
import java.util.*

class NoteConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
