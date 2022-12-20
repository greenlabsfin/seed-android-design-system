package com.greenlabsfin.design.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.greenlabsfin.design.core.color.GfColorScheme

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
        val containerPrimary: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().container.primary,
                contentColor = GfColorScheme().contents.onPrimary,
            )

        val outlinePrimary: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().container.neutralPrimary,
                contentColor = GfColorScheme().container.primary,
                borderColor = GfColorScheme().container.primary,
                disabledBorderColor = GfColorScheme().contents.neutralTertiary.copy(
                    alpha = 0.6f
                )
            )

        val tintPrimary: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().container.secondary,
                contentColor = GfColorScheme().contents.primary
            )

        val outlineNeutral: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().container.neutralPrimary,
                contentColor = GfColorScheme().contents.neutralPrimary,
                borderColor = GfColorScheme().container.outline,
                disabledBorderColor = GfColorScheme().contents.neutralTertiary.copy(
                    alpha = 0.6f
                )
            )

        val tintNeutral: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().container.neutralSecondary,
                contentColor = GfColorScheme().contents.neutralPrimary
            )

        val containerNegative: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().contents.error,
                contentColor = GfColorScheme().contents.onPrimary
            )

        val tintNegative: GFButtonColor =
            GFButtonColors(
                backgroundColor = GfColorScheme().container.error,
                contentColor = GfColorScheme().contents.error
            )

        @Composable
        fun custom(
            backgroundColor: Color,
            contentColor: Color,
            disabledBackgroundColor: Color =
                GfColorScheme().container.neutralSecondary.copy(alpha = 0.6f),
            disabledContentColor: Color =
                GfColorScheme().contents.neutralPrimary.copy(alpha = 0.6f),
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