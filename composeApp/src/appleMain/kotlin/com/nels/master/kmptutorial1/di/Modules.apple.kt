package com.nels.master.kmptutorial1.di

import org.koin.dsl.module
import com.nels.master.kmptutorial1.data.database.getDatabaseBuider
import org.koin.core.module.dsl.factoryOf

actual val nativeModule = module {
        single {getDatabaseBuider()}
        factoryOf(::IosRegionDataSource) bind RegionDatasource::class
}