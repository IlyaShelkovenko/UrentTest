package org.example.urent_test.feature.cities_list.data

import org.example.urent_test.core.network.UrentDevApi
import org.example.urent_test.feature.cities_list.domain.CitiesDomain
import org.example.urent_test.feature.cities_list.domain.CitiesRepository
import org.example.urent_test.feature.cities_list.domain.City

class CitiesRepositoryImpl(
    private val urentDevApi: UrentDevApi,
    private val countryNameMapper: CountryNameMapper
) : CitiesRepository {

    override suspend fun getCities(
        query: String?,
        page: Int,
        limit: Int
    ) : CitiesDomain{
        val response = urentDevApi.getCities(query, page, limit)
        return CitiesDomain(
            cities = response.items.map { city ->
                City(
                    id = city.id,
                    name = city.name,
                    country = countryNameMapper.map(city.country),
                    population = city.pop.toString()
                )
            }
        )
    }
}