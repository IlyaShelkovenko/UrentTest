package org.example.urent_test.feature.city_information.di

import org.example.urent_test.feature.city_information.presentation.CityInformationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cityInformationModule = module {
    viewModelOf(::CityInformationViewModel)
}
