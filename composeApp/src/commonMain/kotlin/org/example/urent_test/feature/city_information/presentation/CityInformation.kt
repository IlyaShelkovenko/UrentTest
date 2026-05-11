package org.example.urent_test.feature.city_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.components.CityInfoField
import org.example.urent_test.design.components.PrimaryButton
import org.example.urent_test.design.components.TopBar
import org.example.urent_test.design.modifier.screenInsets
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import urenttest.composeapp.generated.resources.Res
import urenttest.composeapp.generated.resources.button_title_search_information
import urenttest.composeapp.generated.resources.info_label_city
import urenttest.composeapp.generated.resources.info_label_country
import urenttest.composeapp.generated.resources.info_label_population
import urenttest.composeapp.generated.resources.label_person_unit
import urenttest.composeapp.generated.resources.top_bar_title_city_information

@Composable
fun CityInformation(
    cityParams: CityParams,
    onGoBack: () -> Unit,
    viewModel: CityInformationViewModel = koinViewModel(
        key = cityParams.name,
        parameters = { parametersOf(cityParams) }
    )
) {
    val state by viewModel.collectAsState()
    val uriHandler = LocalUriHandler.current

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is CityInformationSideEffect.OpenBrowser -> uriHandler.openUri(sideEffect.url)
        }
    }

    CityInformationScreen(
        state = state,
        onAction = viewModel::onAction,
        onGoBack = onGoBack
    )
}

@Composable
fun CityInformationScreen(
    state: CityInformationState,
    onAction: (CityInformationAction) -> Unit,
    onGoBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cityParams = state.cityParams
    val personUnit = stringResource(Res.string.label_person_unit)
    val formattedPopulation = cityParams.population.formatPopulation(personUnit)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ColorTokens.Background)
            .screenInsets()
            .padding(horizontal = 20.dp, vertical = 32.dp)
    ) {
        TopBar(
            title = stringResource(Res.string.top_bar_title_city_information),
            onBackClick = onGoBack
        )

        Column(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            CityInfoField(label = stringResource(Res.string.info_label_city), value = cityParams.name)
            CityInfoField(label = stringResource(Res.string.info_label_country), value = cityParams.country)
            CityInfoField(label = stringResource(Res.string.info_label_population), value = formattedPopulation)
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = stringResource(Res.string.button_title_search_information),
            onClick = { onAction(CityInformationAction.OnSearchInformationClick) }
        )
    }
}

private fun String.formatPopulation(personUnit: String): String {
    val digits = filter { it.isDigit() }
    if (digits.isEmpty()) return this

    val formattedNumber = digits
        .reversed()
        .chunked(3)
        .joinToString(separator = " ")
        .reversed()

    return "$formattedNumber $personUnit"
}

@Preview
@Composable
private fun CityInformationPreview() {
    UrentTestTheme {
        CityInformationScreen(
            state = CityInformationState(
                cityParams = CityParams(
                    name = "Москва",
                    country = "Россия",
                    population = "12 655 000 чел"
                )
            ),
            onAction = {},
            onGoBack = {}
        )
    }
}
