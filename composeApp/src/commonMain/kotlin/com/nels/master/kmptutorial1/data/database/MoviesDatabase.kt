package com.nels.master.kmptutorial1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nels.master.kmptutorial1.data.Movie

const val DATABASE_NAME = "movies_database.db"

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}


