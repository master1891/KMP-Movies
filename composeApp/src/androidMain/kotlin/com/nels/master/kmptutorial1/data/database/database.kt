package com.nels.master.kmptutorial1.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MoviesDatabase> {
    val dbFile = context.getDatabasePath(DATABASE_NAME)
    return Room.databaseBuilder(
                context,
        MoviesDatabase::class.java,
        dbFile.absolutePath
    )
}