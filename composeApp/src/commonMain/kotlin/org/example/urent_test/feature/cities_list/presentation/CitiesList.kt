package org.example.urent_test.feature.cities_list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.components.ListItem
import org.example.urent_test.design.components.TopBar
import org.example.urent_test.design.components.UrentSearchField
import org.example.urent_test.design.modifier.screenInsets
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.example.urent_test.feature.cities_list.domain.City
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import urenttest.composeapp.generated.resources.Res
import urenttest.composeapp.generated.resources.button_text_retry
import urenttest.composeapp.generated.resources.error_message_no_cities
import urenttest.composeapp.generated.resources.error_message_something_went_wrong
import urenttest.composeapp.generated.resources.top_bar_title_cities_list

@Composable
fun CitiesList(
    onCityClick: (City) -> Unit,
    viewModel: CitiesListViewModel = koinViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CitiesListSideEffect.NavigateToCity -> onCityClick(sideEffect.city)
        }
    }

    CitiesListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CitiesListScreen(
    state: CitiesListState,
    onAction: (CitiesListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ColorTokens.Background)
            .screenInsets()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        TopBar(title = stringResource(Res.string.top_bar_title_cities_list))
        UrentSearchField(
            value = state.query,
            onValueChange = { onAction(CitiesListAction.OnQueryChange(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when {
                state.isLoading && state.cities.isEmpty() -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.cities.isEmpty() -> {
                    if (state.errorMessage != null) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(Res.string.error_message_something_went_wrong),
                                color = ColorTokens.Text.Primary,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(Modifier.height(8.dp))
                            Button(
                                onClick = { onAction(CitiesListAction.OnRetryClick) },

                            ){
                                Text(stringResource(Res.string.button_text_retry))
                            }
                        }

                    } else {
                        Text(
                            text = stringResource(Res.string.error_message_no_cities),
                            modifier = Modifier.align(Alignment.Center),
                            color = ColorTokens.Text.Primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(top = 8.dp)
                    ) {
                        items(
                            items = state.cities,
                            key = { it.id }
                        ) { city ->
                            ListItem(
                                title = "${city.name}, ${city.country}",
                                onClick = { onAction(CitiesListAction.OnCityClick(city)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CitiesListScreenPreview() {
    UrentTestTheme {
        CitiesListScreen(
            state = CitiesListState(
                cities = listOf(
                    City(id = 0, name = "Москва", country = "Россия", population = "12655000"),
                    City(
                        id = 0,
                        name = "Лондон",
                        country = "Великобритания",
                        population = "8799800"
                    ),
                    City(id = 0, name = "Париж", country = "Франция", population = "2161000")
                )
            ),
            onAction = {}
        )
    }
}
