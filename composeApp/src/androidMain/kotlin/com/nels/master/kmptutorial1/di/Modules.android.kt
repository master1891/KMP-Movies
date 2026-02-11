package com.nels.master.kmptutorial1.di

import com.nels.master.kmptutorial1.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule = module {
    single {
        getDatabaseBuilder(get())
    }
}