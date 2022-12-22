package com.greenlabsfin.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.typo.GfTypoScheme

@Stable
interface GfCountStyle {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun textColor(enabled: Boolean): State<Color>

    @Composable
    fun textStyle(textStyle: TextStyle, enabled: Boolean): State<TextStyle>
}

object GfCount {
    object Style {
        val primary: GfCountStyle
            @Composable
            get() = GfCounts(
                textColor = GfTheme.colorScheme.contents.onPrimary,
                backgroundColor = GfTheme.colorScheme.contents.primary,
            )

        val secondary: GfCountStyle
            @Composable
            get() = GfCounts(
                textColor = GfTheme.colorScheme.contents.primary,
                backgroundColor = GfTheme.colorScheme.container.secondary,
            )

        val neutral: GfCountStyle
            @Composable
            get() = GfCounts(
                textColor = GfTheme.colorScheme.contents.onPrimary,
                backgroundColor = GfTheme.colorScheme.contents.neutralPrimary,
            )

        val errorPrimary: GfCountStyle
            @Composable
            get() = GfCounts(
                textColor = GfTheme.colorScheme.contents.error,
                backgroundColor = GfTheme.colorScheme.contents.onPrimary,
            )

        val errorSecondary: GfCountStyle
            @Composable
            get() = GfCounts(
                textColor = GfTheme.colorScheme.contents.onPrimary,
                backgroundColor = GfTheme.colorScheme.contents.error,
            )

        @Composable
        fun custom(
            textColor: Color,
            backgroundColor: Color,
        ): GfCountStyle = GfCounts(
            textColor = textColor,
            backgroundColor = backgroundColor,
        )

        @Composable
        fun getDefault(buttonColor: GFButtonColor): GfCountStyle =
            when (buttonColor) {
                GFButton.Style.containerPrimary -> secondary
                GFButton.Style.outlinePrimary -> primary
                GFButton.Style.tintPrimary -> primary
                GFButton.Style.outlineNeutral -> neutral
                GFButton.Style.tintNeutral -> neutral
                GFButton.Style.containerNegative -> errorPrimary
                GFButton.Style.tintNegative -> errorSecondary
                else -> primary
            }
    }
}

@Immutable
private data class GfCounts(
    val textColor: Color,
    val backgroundColor: Color,
) : GfCountStyle {
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
    modifier: Modifier = Modifier,
    count: Int,
    style: GfCountStyle,
    enabled: Boolean = true,
) {
    val backgroundColor by style.backgroundColor(enabled = enabled)
    val textColor by style.textColor(enabled = enabled)
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
