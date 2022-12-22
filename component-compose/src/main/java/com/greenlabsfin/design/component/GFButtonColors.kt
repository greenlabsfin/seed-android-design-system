package com.greenlabsfin.design.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme

@Stable
interface GFButtonColor {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun contentColor(enabled: Boolean): State<Color>

    @Composable
    fun borderColor(enabled: Boolean): State<Color>
}

object GFButton {
    object Style {
        val containerPrimary: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.container.primary,
                contentColor = GfTheme.colorScheme.contents.onPrimary,
            )

        val outlinePrimary: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.container.neutralPrimary,
                contentColor = GfTheme.colorScheme.container.primary,
                borderColor = GfTheme.colorScheme.container.primary,
                disabledBorderColor = GfTheme.colorScheme.contents.neutralTertiary.copy(
                    alpha = 0.6f
                )
            )

        val tintPrimary: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.container.secondary,
                contentColor = GfTheme.colorScheme.contents.primary
            )

        val outlineNeutral: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.container.neutralPrimary,
                contentColor = GfTheme.colorScheme.contents.neutralPrimary,
                borderColor = GfTheme.colorScheme.container.outline,
                disabledBorderColor = GfTheme.colorScheme.contents.neutralTertiary.copy(
                    alpha = 0.6f
                )
            )

        val tintNeutral: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.container.neutralSecondary,
                contentColor = GfTheme.colorScheme.contents.neutralPrimary
            )

        val containerNegative: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.contents.error,
                contentColor = GfTheme.colorScheme.contents.onPrimary
            )

        val tintNegative: GFButtonColor
            @Composable
            get() = GFButtonColors(
                backgroundColor = GfTheme.colorScheme.container.error,
                contentColor = GfTheme.colorScheme.contents.error
            )

        @Composable
        fun custom(
            backgroundColor: Color,
            contentColor: Color,
            disabledBackgroundColor: Color =
                GfTheme.colorScheme.container.neutralSecondary.copy(alpha = 0.6f),
            disabledContentColor: Color =
                GfTheme.colorScheme.contents.neutralPrimary.copy(alpha = 0.6f),
            borderColor: Color = Color.Transparent,
            disabledBorderColor: Color = Color.Transparent,
        ): GFButtonColor =
            GFButtonColors(
                backgroundColor,
                contentColor,
                disabledBackgroundColor,
                disabledContentColor,
                borderColor,
                disabledBorderColor
            )
    }
}

@Immutable
private data class GFButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color =
        GfColorScheme().container.neutralSecondary.copy(alpha = 0.6f),
    private val disabledContentColor: Color =
        GfColorScheme().contents.neutralPrimary.copy(alpha = 0.6f),
    private val borderColor: Color = Color.Transparent,
    private val disabledBorderColor: Color = Color.Transparent,
) : GFButtonColor {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) contentColor else disabledContentColor)

    @Composable
    override fun borderColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) borderColor else disabledBorderColor)
}
