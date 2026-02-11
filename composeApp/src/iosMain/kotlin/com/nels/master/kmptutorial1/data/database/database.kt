package com.nels.master.kmptutorial1.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuider(): RoomDatabase.Builder<MoviesDatabase> {
    val path = NSHomeDirectory() + "/$DATABASE_NAME"

    return Room.databaseBuilder<MoviesDatabase>(
        name = path,
        factory = {
            MoviesDatabase::class.instianteImpl()
        }
    ).setDriver(BundledSQLiteDriver())

}