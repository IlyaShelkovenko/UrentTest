package org.example.urent_test.city_information.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.urent_test.design.components.CityInfoField
import org.example.urent_test.design.components.PrimaryButton
import org.example.urent_test.design.components.TopBar
import org.example.urent_test.design.modifier.screenInsets
import org.example.urent_test.design.theme.UrentTestTheme
import org.example.urent_test.design.tokens.ColorTokens
import org.jetbrains.compose.resources.stringResource
import urenttest.composeapp.generated.resources.Res
import urenttest.composeapp.generated.resources.button_title_search_information
import urenttest.composeapp.generated.resources.info_label_city
import urenttest.composeapp.generated.resources.info_label_country
import urenttest.composeapp.generated.resources.info_label_population
import urenttest.composeapp.generated.resources.top_bar_title_city_information

@Composable
fun CityInformation(
    cityParams: CityParams,
    onGoBack: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            CityInfoField(label = stringResource(Res.string.info_label_population), value = cityParams.population)
        }

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = stringResource(Res.string.button_title_search_information),
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun CityInformationPreview() {
    UrentTestTheme {
        CityInformation(
            cityParams = CityParams(
                name = "Москва",
                country = "Россия",
                population = "12 655 000 чел"
            ),
            onGoBack = {}
        )
    }
}
