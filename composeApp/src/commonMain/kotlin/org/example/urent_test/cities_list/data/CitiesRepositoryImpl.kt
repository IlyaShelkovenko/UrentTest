package org.example.urent_test.cities_list.data

import org.example.urent_test.cities_list.domain.CitiesDomain
import org.example.urent_test.cities_list.domain.CitiesRepository
import org.example.urent_test.cities_list.domain.City
import org.example.urent_test.core.network.UrentDevApi

class CitiesRepositoryImpl(
    private val urentDevApi: UrentDevApi
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
                    country = city.country,
                    population = city.pop.toString()
                )
            }
        )
    }
}