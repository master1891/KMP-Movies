package com.nels.master.kmptutorial1.di

import org.koin.dsl.module
import com.nels.master.kmptutorial1.data.database.getDatabaseBuider
actual val nativeModule = module {
        single {getDatabaseBuider()}
}