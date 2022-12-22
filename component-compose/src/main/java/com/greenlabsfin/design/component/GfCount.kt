package com.greenlabsfin.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greenlabsfin.design.component.util.GfPreview
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme
import com.greenlabsfin.design.core.typo.GfTypoScheme

@Stable
interface GfCountColors {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun textColor(enabled: Boolean): State<Color>

    @Composable
    fun textStyle(textStyle: TextStyle, enabled: Boolean): State<TextStyle>
}

object GfCountDefaults {
    object Colors {
        val primary: GfCountColors
            @Composable
            get() = DefaultGfCountColors(
                textColor = GfTheme.colorScheme.contents.onPrimary,
                backgroundColor = GfTheme.colorScheme.contents.primary,
            )

        val secondary: GfCountColors
            @Composable
            get() = DefaultGfCountColors(
                textColor = GfTheme.colorScheme.contents.primary,
                backgroundColor = GfTheme.colorScheme.container.secondary,
            )

        val neutral: GfCountColors
            @Composable
            get() = DefaultGfCountColors(
                textColor = GfTheme.colorScheme.contents.onInverse,
                backgroundColor = GfTheme.colorScheme.container.inverse,
            )

        val errorPrimary: GfCountColors
            @Composable
            get() = DefaultGfCountColors(
                textColor = GfTheme.colorScheme.contents.error,
                backgroundColor = GfTheme.colorScheme.contents.onPrimary,
            )

        val errorSecondary: GfCountColors
            @Composable
            get() = DefaultGfCountColors(
                textColor = GfTheme.colorScheme.contents.onPrimary,
                backgroundColor = GfTheme.colorScheme.contents.error,
            )
    }

    @Composable
    fun getByButtonColor(buttonColor: GFButtonColor): GfCountColors =
        when (buttonColor) {
            GFButton.Style.containerPrimary -> Colors.secondary
            GFButton.Style.outlinePrimary -> Colors.primary
            GFButton.Style.tintPrimary -> Colors.primary
            GFButton.Style.outlineNeutral -> Colors.neutral
            GFButton.Style.tintNeutral -> Colors.neutral
            GFButton.Style.containerNegative -> Colors.errorPrimary
            GFButton.Style.tintNegative -> Colors.errorSecondary
            else -> Colors.primary
        }

    @Composable
    fun chipColors(
        textColor: Color = Color.Unspecified,
        backgroundColor: Color = Color.Unspecified,
    ): GfCountColors = DefaultGfCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
    )
}

@Immutable
private data class DefaultGfCountColors(
    val textColor: Color,
    val backgroundColor: Color,
) : GfCountColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) backgroundColor else backgroundColor.copy(alpha = .6f))

    @Composable
    override fun textColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) textColor else textColor.copy(alpha = .3f))

    @Composable
    override fun textStyle(textStyle: TextStyle, enabled: Boolean): State<TextStyle> =
        rememberUpdatedState(
            textStyle.merge(
                TextStyle(
                    color = if (enabled) textColor else textColor.copy(alpha = .3f)
                )
            )
        )
}


@Composable
internal fun GfCount(
    count: Int,
    colors: GfCountColors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val backgroundColor by colors.backgroundColor(enabled = enabled)
    val textColor by colors.textColor(enabled = enabled)
    val text = count.toString()
    val size = 16.dp

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = size, minHeight = size)
            .clip(CircleShape)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = text,
            style = counterTextStyle,
            color = textColor,
        )
    }
}

private val counterTextStyle = GfTypoScheme.custom(
    size = 12.sp,
    weight = FontWeight.Medium
)

@GfPreview
@Composable
fun GfCountPreview() {
    GfTheme(colorScheme = GfColorScheme.default(isSystemInDarkTheme())) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            GfCount(count = 3, colors = GfCountDefaults.Colors.primary)
            GfCount(count = 33, colors = GfCountDefaults.Colors.secondary)
            GfCount(count = 3, colors = GfCountDefaults.Colors.neutral)
            GfCount(count = 333, colors = GfCountDefaults.Colors.errorPrimary)
            GfCount(count = 3, colors = GfCountDefaults.Colors.errorSecondary)
        }
    }
}
