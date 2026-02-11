package com.nels.master.kmptutorial1.di

import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.nels.master.kmptutorial1.data.AndroidRegisdataSource
import com.nels.master.kmptutorial1.data.RegionDatasource
import com.nels.master.kmptutorial1.data.database.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val nativeModule = module {
    single {
        getDatabaseBuilder(get())
    }
    factory { Geocoder(get()) }
    factory { LocationServices.getFusedLocationProviderClient(androidContext()) }
    factoryOf(::AndroidRegisdataSource) bind RegionDatasource::class

}