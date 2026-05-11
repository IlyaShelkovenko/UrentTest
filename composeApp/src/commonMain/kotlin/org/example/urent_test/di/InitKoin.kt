package org.example.urent_test.di

import org.example.urent_test.AppConfig
import org.example.urent_test.core.network.di.networkModule
import org.example.urent_test.feature.cities_list.di.citiesListModule
import org.example.urent_test.feature.city_information.di.cityInformationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appConfig: AppConfig, config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            networkModule(appConfig),
            citiesListModule,
            cityInformationModule
        )
    }
}
