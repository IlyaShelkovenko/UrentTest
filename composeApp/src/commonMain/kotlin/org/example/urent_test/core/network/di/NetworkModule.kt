package org.example.urent_test.core.network.di

import io.ktor.client.HttpClient
import org.example.urent_test.AppConfig
import org.example.urent_test.core.network.UrentDevApi
import org.example.urent_test.core.network.createHttpClient
import org.koin.dsl.module

fun networkModule(appConfig: AppConfig) = module {

    single<HttpClient> {
        createHttpClient(appConfig.baseUrl)
    }

    single {
        UrentDevApi(get())
    }
}