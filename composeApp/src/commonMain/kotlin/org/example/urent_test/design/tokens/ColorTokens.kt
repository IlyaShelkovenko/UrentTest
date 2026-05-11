package org.example.urent_test.design.tokens

import androidx.compose.ui.graphics.Color

object ColorTokens {
    val Background = Color(0xFFFFFFFF)
    val Surface = Color(0xFFFFFFFF)
    val SurfaceMuted = Color(0xFFF6F6F7)
    val SurfacePressed = Color(0xFFEDEDF1)
    val InputBorder = Color(0x00FFFFFF)
    val Divider = Color(0xFFECECF0)

    object Text {
        val Primary = Color(0xFF25222B)
        val Placeholder = Color(0xFFB9B7BE)
        val OnAction = Color(0xFFFFFFFF)
    }

    object Button {
        val Default = Color(0xFF804AFF)
        val Pressed = Color(0xFF683BD3)
    }

    object Input {
        val Background = Color(0xFFF6F6F7)
        val BorderDefault = Color(0x00FFFFFF)
        val BorderFocused = Color(0xFF804AFF)
    }

    val TextSecondary = Color(0xFF5D5D66)
    val TextPlaceholder = Text.Placeholder
    val IconDefault = Color(0xFF1D1D24)
    val IconMuted = Color(0xFF6D6D78)

    val Accent = Button.Default
    val AccentPressed = Button.Pressed
    val AccentFocus = Input.BorderFocused
    val OnAccent = Text.OnAction
}
