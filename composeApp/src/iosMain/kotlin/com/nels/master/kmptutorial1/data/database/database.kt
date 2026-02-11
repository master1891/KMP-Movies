package com.nels.master.kmptutorial1.data.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuider(): MoviesDatabase {
    val path = NSHomeDirectory() + "/$DATABASE_NAME"

    return Room.databaseBuilder(
        name = path,
        factory = {
            MoviesDatabase::class.instianteImpl()
        }
    ).setDriver(BundledSQLiteDriver())

}