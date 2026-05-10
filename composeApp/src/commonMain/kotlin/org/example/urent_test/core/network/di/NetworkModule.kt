package org.example.urent_test.core.network.di

import io.ktor.client.HttpClient
import org.example.urent_test.AppConfig
import org.example.urent_test.core.network.UrentApi
import org.example.urent_test.core.network.createHttpClient
import org.koin.dsl.module

val networkModule = module {

    single {
        AppConfig(
            baseUrl = "http://dev-dep.tools.urent.tech:8080/api/"
        )
    }

    single<HttpClient> {
        val config = get<AppConfig>()

        createHttpClient(config.baseUrl)
    }

    single {
        UrentApi(get())
    }
}