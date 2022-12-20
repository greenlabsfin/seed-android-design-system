package com.greenlabsfin.design.core.typo

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.greenlabsfin.design.core.R

@Immutable
data class GfTypoScheme(
    val headline: HeadlineTypoScheme = HeadlineTypoScheme(),
    val body: BodyTypoScheme = BodyTypoScheme(),
    val caption: CaptionTypoScheme = CaptionTypoScheme(),
) {
    data class SizeValue(
        val xLarge: TextUnit = TextUnit.Unspecified,
        val large: TextUnit = TextUnit.Unspecified,
        val medium: TextUnit = TextUnit.Unspecified,
        val small: TextUnit = TextUnit.Unspecified,
        val xSmall: TextUnit = TextUnit.Unspecified,
    )

    enum class SizeType {
        XLarge,
        Large,
        Medium,
        Small,
        XSmall
    }
}

@Immutable
data class HeadlineTypoScheme(
    val xLargeBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.XLarge,
        weight = FontWeight.Bold
    ),
    val largeBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Large,
        weight = FontWeight.Bold
    ),
    val mediumBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Medium,
        weight = FontWeight.Bold
    ),
    val smallBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Small,
        weight = FontWeight.Bold
    ),
    val smallRegular: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Small,
        weight = FontWeight.Regular
    ),
) {

    companion object {
        val defaultSize = GfTypoScheme.SizeValue(
            xLarge = 32.sp,
            large = 24.sp,
            medium = 22.sp,
            small = 19.sp,
        )
    }
}

fun HeadlineTypoScheme.Companion.default(
    sizeType: GfTypoScheme.SizeType,
    sizeValue: GfTypoScheme.SizeValue = defaultSize,
    weight: FontWeight,
): TextStyle {
    val size = sizeValue.sizeOf(sizeType)

    return TextStyle(
        fontFamily = fontFamily,
        fontSize = size,
        lineHeight = when (Locale.current.language) {
            KO, JA -> size.times(1.4f)
            else -> size.times(1.3f)
        },
        letterSpacing = size.letterSpacing,
        fontWeight = weight,
    )
}

@Immutable
data class BodyTypoScheme(
    val xLargeBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.XLarge,
        weight = FontWeight.Bold
    ),
    val xLargeRegular: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.XLarge,
        weight = FontWeight.Regular
    ),
    val largeBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Large,
        weight = FontWeight.Bold
    ),
    val largeRegular: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Large,
        weight = FontWeight.Regular
    ),
    val mediumBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Medium,
        weight = FontWeight.Bold
    ),
    val mediumMedium: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Medium,
        weight = FontWeight.Medium
    ),
    val mediumRegular: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Medium,
        weight = FontWeight.Regular
    ),
    val smallBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Small,
        weight = FontWeight.Bold
    ),
    val smallMedium: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Small,
        weight = FontWeight.Medium
    ),
    val smallRegular: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.Small,
        weight = FontWeight.Regular
    ),
) {
    companion object {
        val defaultSize = GfTypoScheme.SizeValue(
            xLarge = 22.sp,
            large = 19.sp,
            medium = 17.sp,
            small = 15.sp,
        )
    }
}

fun BodyTypoScheme.Companion.default(
    sizeType: GfTypoScheme.SizeType,
    sizeValue: GfTypoScheme.SizeValue = defaultSize,
    weight: FontWeight,
): TextStyle {
    val size = sizeValue.sizeOf(sizeType)

    return TextStyle(
        fontFamily = fontFamily,
        fontSize = size,
        lineHeight = size.times(1.5f),
        letterSpacing = size.letterSpacing,
        fontWeight = weight,
    )
}

@Immutable
data class CaptionTypoScheme(
    val xSmallRegular: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.XSmall,
        weight = FontWeight.Regular
    ),
    val xSmallBold: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.XSmall,
        weight = FontWeight.Bold
    ),
    val xSmallMedium: TextStyle = default(
        sizeType = GfTypoScheme.SizeType.XSmall,
        weight = FontWeight.Medium
    ),
) {
    companion object {
        val defaultSize = GfTypoScheme.SizeValue(
            xSmall = 13.sp
        )
    }
}

fun CaptionTypoScheme.Companion.default(
    sizeType: GfTypoScheme.SizeType,
    sizeValue: GfTypoScheme.SizeValue = defaultSize,
    weight: FontWeight,
): TextStyle {
    val size = sizeValue.sizeOf(sizeType)

    return TextStyle(
        fontFamily = fontFamily,
        fontSize = size,
        lineHeight = size.times(1.4f),
        letterSpacing = size.letterSpacing,
        fontWeight = weight,
    )
}


private const val KO = "ko"
private const val JA = "ja"

private val fontFamily = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Normal)
)

val FontWeight.Companion.Regular: FontWeight
    get() = Normal

private val TextUnit.letterSpacing: TextUnit
    get() = when (Locale.current.language) {
        KO, JA -> this.times(.02f).unaryMinus()
        else -> 0.sp
    }

fun GfTypoScheme.SizeValue.sizeOf(
    sizeType: GfTypoScheme.SizeType,
): TextUnit = when (sizeType) {
    GfTypoScheme.SizeType.XLarge -> xLarge
    GfTypoScheme.SizeType.Large -> large
    GfTypoScheme.SizeType.Medium -> medium
    GfTypoScheme.SizeType.Small -> small
    GfTypoScheme.SizeType.XSmall -> xSmall
}
