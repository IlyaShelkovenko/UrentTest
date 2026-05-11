package org.example.urent_test.feature.cities_list.di

import org.example.urent_test.feature.cities_list.data.CitiesRepositoryImpl
import org.example.urent_test.feature.cities_list.data.CountryNameMapper
import org.example.urent_test.feature.cities_list.domain.CitiesRepository
import org.example.urent_test.feature.cities_list.presentation.CitiesListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val citiesListModule = module {
    viewModelOf(::CitiesListViewModel)

    single<CitiesRepository> {
        CitiesRepositoryImpl(get(), get())
    }

    single {
        CountryNameMapper()
    }
}
