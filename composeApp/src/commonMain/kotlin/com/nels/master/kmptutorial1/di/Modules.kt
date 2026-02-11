package com.nels.master.kmptutorial1.di

import androidx.room.RoomDatabase
import com.nels.master.kmptutorial1.BuildConfig
import com.nels.master.kmptutorial1.data.MoviesRepository
import com.nels.master.kmptutorial1.data.RegionRepository
import com.nels.master.kmptutorial1.data.remote.MoviesService
import com.nels.master.kmptutorial1.data.database.MoviesDao
import com.nels.master.kmptutorial1.data.database.MoviesDatabase
import com.nels.master.kmptutorial1.ui.screens.detail.DetailViewModel
import com.nels.master.kmptutorial1.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol.Companion.HTTPS
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {

    single(named("apiKey")) {
        BuildConfig.API_KEY
    }

    single<MoviesDao> {
        val dbBoilder = get<RoomDatabase.Builder<MoviesDatabase>>()
        dbBoilder.build().moviesDao()
    }
}


val dataModule = module {
//    factory { MoviesRepository(moviesDao = get(), moviesService = get()) }
//    factory { MoviesService(client = get()) }

    factoryOf(::MoviesRepository)
    factoryOf(::RegionRepository)
    factoryOf(::MoviesService)

    single<HttpClient> {

        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", BuildConfig.API_KEY)
                }
            }
        }
    }
}


val viewModelModules = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
}


expect val nativeModule: Module

fun initkoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            appModule,
            dataModule,
            viewModelModules,
            nativeModule
        )
    }
}
