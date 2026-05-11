package org.example.urent_test.design.tokens

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

object TypographyTokens {
    val TitleMedium16 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    )

    val BodyRegular16 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    )

    val Title3SemiBold = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    )

    val SemiBold18Centered = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.sp,
        textAlign = TextAlign.Center
    )
}

val Typography = Typography(
    titleMedium = TypographyTokens.TitleMedium16,
    bodyMedium = TypographyTokens.BodyRegular16,
    headlineMedium = TypographyTokens.Title3SemiBold,
    headlineSmall = TypographyTokens.SemiBold18Centered,
)
