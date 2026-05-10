package org.example.urent_test.di

import org.example.urent_test.AppConfig
import org.example.urent_test.core.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appConfig: AppConfig, config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            networkModule(appConfig)
        )
    }
}