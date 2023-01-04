package com.greenlabsfin.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greenlabsfin.design.component.util.SeedPreview
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.SeedColorScheme
import com.greenlabsfin.design.core.typo.SeedTypoScheme

object SeedCountDefaults {
    object Colors {
        @Composable
        fun primary(
            textColor: Color = SeedTheme.colorScheme.contents.onPrimary,
            backgroundColor: Color = SeedTheme.colorScheme.contents.primary
        ): SeedCount.Colors = DefaultSeedCountColors(
            textColor = textColor,
            backgroundColor = backgroundColor
        )

        @Composable
        fun secondary(
            textColor: Color = SeedTheme.colorScheme.contents.primary,
            backgroundColor: Color = SeedTheme.colorScheme.container.secondary
        ): SeedCount.Colors = DefaultSeedCountColors(
            textColor = textColor,
            backgroundColor = backgroundColor
        )

        @Composable
        fun neutral(
            textColor: Color = SeedTheme.colorScheme.contents.onInverse,
            backgroundColor: Color = SeedTheme.colorScheme.container.inverse
        ): SeedCount.Colors = DefaultSeedCountColors(
            textColor = textColor,
            backgroundColor = backgroundColor
        )

        @Composable
        fun errorPrimary(
            textColor: Color = SeedTheme.colorScheme.contents.error,
            backgroundColor: Color = SeedTheme.colorScheme.contents.onPrimary
        ): SeedCount.Colors = DefaultSeedCountColors(
            textColor = textColor,
            backgroundColor = backgroundColor
        )

        @Composable
        fun errorSecondary(
            textColor: Color = SeedTheme.colorScheme.contents.onPrimary,
            backgroundColor: Color = SeedTheme.colorScheme.contents.error
        ): SeedCount.Colors = DefaultSeedCountColors(
            textColor = textColor,
            backgroundColor = backgroundColor
        )

    }

    @Composable
    fun getByButtonColor(buttonColor: SeedButton.Colors): SeedCount.Colors =
        when (buttonColor) {
            SeedButtonDefaults.Colors.containerPrimary() -> Colors.secondary()
            SeedButtonDefaults.Colors.outlinePrimary() -> Colors.primary()
            SeedButtonDefaults.Colors.tintPrimary() -> Colors.primary()
            SeedButtonDefaults.Colors.outlineNeutral() -> Colors.neutral()
            SeedButtonDefaults.Colors.tintNeutral() -> Colors.neutral()
            SeedButtonDefaults.Colors.containerNegative() -> Colors.errorPrimary()
            SeedButtonDefaults.Colors.tintNegative() -> Colors.errorSecondary()
            else -> Colors.primary()
        }

    @Composable
    fun countColors(
        textColor: Color = Color.Unspecified,
        backgroundColor: Color = Color.Unspecified,
    ): SeedCount.Colors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
    )
}

@Immutable
private data class DefaultSeedCountColors(
    val textColor: Color,
    val backgroundColor: Color,
) : SeedCount.Colors {
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

object SeedCount {
    enum class Size(val value: Dp) {
        Large(16.dp),
        Medium(14.dp),
        Small(12.dp),
        ;

        val fontStyle: TextStyle
            get() = when (this) {
                Large -> SeedTypoScheme.custom(
                    size = 12.sp,
                    weight = FontWeight.Medium
                )
                Medium -> SeedTypoScheme.custom(
                    size = 10.sp,
                    weight = FontWeight.Medium
                )
                Small -> SeedTypoScheme.custom(
                    size = 8.sp,
                    weight = FontWeight.Medium
                )
            }
    }

    @Stable
    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean): State<Color>

        @Composable
        fun textColor(enabled: Boolean): State<Color>

        @Composable
        fun textStyle(textStyle: TextStyle, enabled: Boolean): State<TextStyle>
    }
}

@Composable
internal fun SeedCount(
    count: Int,
    colors: SeedCount.Colors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: SeedCount.Size = SeedCount.Size.Large,
) {
    val backgroundColor by colors.backgroundColor(enabled = enabled)
    val textColor by colors.textColor(enabled = enabled)
    val text = count.toString()

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = size.value, minHeight = size.value)
            .clip(CircleShape)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = text,
            style = size.fontStyle,
            color = textColor,
        )
    }
}

private val counterTextStyle = SeedTypoScheme.custom(
    size = 12.sp,
    weight = FontWeight.Medium
)

@SeedPreview
@Composable
fun SeedCountPreview() {
    SeedTheme(colorScheme = SeedColorScheme.default(isSystemInDarkTheme())) {
        Surface(color = SeedTheme.colorScheme.container.background) {
            Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SeedCount(count = 3, colors = SeedCountDefaults.Colors.primary())
                SeedCount(count = 33, colors = SeedCountDefaults.Colors.secondary())
                SeedCount(
                    size = SeedCount.Size.Small,
                    count = 3,
                    colors = SeedCountDefaults.Colors.neutral()
                )
                SeedCount(
                    size = SeedCount.Size.Small,
                    count = 333,
                    colors = SeedCountDefaults.Colors.errorPrimary()
                )
                SeedCount(
                    size = SeedCount.Size.Medium,
                    count = 3,
                    colors = SeedCountDefaults.Colors.errorSecondary(),
                )
            }
        }
    }
}
