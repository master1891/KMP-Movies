package com.nels.master.kmptutorial1

import android.app.Application
import com.nels.master.kmptutorial1.di.initkoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initkoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AndroidApp)
        }

    }
}