package org.example.urent_test.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import org.example.urent_test.design.tokens.ColorTokens
import org.example.urent_test.design.tokens.Typography

private val UrentTestLightColorScheme = lightColorScheme(
    primary = ColorTokens.Accent,
    onPrimary = ColorTokens.OnAccent,
    primaryContainer = ColorTokens.AccentFocus,
    background = ColorTokens.Background,
    onBackground = ColorTokens.Text.Primary,
    surface = ColorTokens.Surface,
    onSurface = ColorTokens.Text.Primary,
    surfaceVariant = ColorTokens.SurfaceMuted,
    onSurfaceVariant = ColorTokens.TextSecondary,
    outline = ColorTokens.Divider
)

@Composable
fun UrentTestTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = UrentTestLightColorScheme,
        typography = Typography,
        content = content
    )
}
