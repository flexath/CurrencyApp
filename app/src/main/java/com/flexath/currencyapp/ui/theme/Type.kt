package com.flexath.currencyapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.flexath.currencyapp.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semi_bold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semi_bold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

enum class CustomFont {
    Inter,
    Poppins
}

fun getFont(font: CustomFont): FontFamily {
    return when (font) {
        CustomFont.Inter -> Inter
        CustomFont.Poppins -> Poppins
    }
}

@Immutable
data class CurrencyTypography(
    val titleLarge: TextStyle = TextStyle.Default,
    val titleMedium: TextStyle = TextStyle.Default,
    val titleSmall: TextStyle = TextStyle.Default,
    val bodyLarge: TextStyle = TextStyle.Default,
    val bodyMedium: TextStyle = TextStyle.Default,
    val bodySmall: TextStyle = TextStyle.Default,
    val labelMedium: TextStyle = TextStyle.Default,
    val labelSmall: TextStyle = TextStyle.Default
)

fun getMediumTypography(font: CustomFont = CustomFont.Inter): CurrencyTypography {
    return CurrencyTypography(
        titleLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.largeText2,
            lineHeight = mediumCompatDimensions.largeText6,
            letterSpacing = 0.2.sp
        ),
        titleMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.largeText1,
            lineHeight = mediumCompatDimensions.largeText5,
            letterSpacing = 0.2.sp
        ),
        titleSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText5,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText4,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText3,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        bodySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText3,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.2.sp
        ),
        labelMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText2,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText1,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        )
    )
}

fun getSmallCompatTypography(font: CustomFont = CustomFont.Inter): CurrencyTypography {
    return CurrencyTypography(
        titleLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText5,
            lineHeight = mediumCompatDimensions.largeText6,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText4,
            lineHeight = mediumCompatDimensions.largeText5,
            letterSpacing = 0.sp
        ),
        titleSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText3,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText2,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText1_n,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        bodySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText1,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        labelMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.smallText5,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.smallText4,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        )
    )
}

fun getLargeCompatTypography(font: CustomFont = CustomFont.Inter): CurrencyTypography {
    return CurrencyTypography(
        titleLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText5,
            lineHeight = mediumCompatDimensions.largeText6,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText4,
            lineHeight = mediumCompatDimensions.largeText5,
            letterSpacing = 0.sp
        ),
        titleSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Medium,
            fontSize = mediumCompatDimensions.mediumText3,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText2,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText1_n,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        bodySmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.mediumText1,
            lineHeight = mediumCompatDimensions.largeText2,
            letterSpacing = 0.sp
        ),
        labelMedium = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.smallText5,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        ),
        labelSmall = TextStyle(
            fontFamily = getFont(font),
            fontWeight = FontWeight.Normal,
            fontSize = mediumCompatDimensions.smallText4,
            lineHeight = mediumCompatDimensions.mediumText3,
            letterSpacing = 0.2.sp
        )
    )
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)