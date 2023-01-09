package com.greenlabsfin.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.SeedColorScheme

@Composable
fun SeedButton(
    modifier: Modifier = Modifier,
    height: SeedButton.Height,
    colors: SeedButton.Colors,
    enabled: Boolean = true,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
    countColors: SeedCountColors? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(height.displayPixel),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.backgroundColor(enabled = enabled).value,
            contentColor = colors.contentColor(enabled = enabled).value
        ),
        border = BorderStroke(1.dp, colors.borderColor(enabled = enabled).value),
        contentPadding = PaddingValues(horizontal = 12.dp),
        shape = RoundedCornerShape(height.radius),
        enabled = enabled
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically) {
            leftIcon?.let {
                Icon(imageVector = it, contentDescription = "SeedButton LeftIcon")
            }
            text?.let {
                Text(text = it, style = height.typography, overflow = TextOverflow.Ellipsis)
            }
            count?.let {
                countColors?.let {
                    SeedCount(
                        count = count,
                        colors = countColors,
                        enabled = enabled,
                    )
                }
            }
            rightIcon?.let {
                Icon(imageVector = it, contentDescription = "SeedButton RightIcon")
            }
        }
    }
}


object SeedButton {
    enum class Height(val displayPixel: Dp) {
        XLarge(64.dp),
        Large(56.dp),
        Medium(48.dp),
        Small(40.dp),
        XSmall(32.dp);

        val typography: TextStyle
            @Composable
            get() = when (this) {
                XLarge -> SeedTheme.typoScheme.body.xLargeBold
                Large -> SeedTheme.typoScheme.body.largeBold
                Medium -> SeedTheme.typoScheme.body.mediumMedium
                else -> SeedTheme.typoScheme.body.smallMedium
            }

        val radius: Dp
            get() = when (this) {
                XLarge, Large -> 12.dp
                Medium -> 10.dp
                Small, XSmall -> 8.dp
            }
    }

    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean): State<Color>

        @Composable
        fun contentColor(enabled: Boolean): State<Color>

        @Composable
        fun borderColor(enabled: Boolean): State<Color>
    }
}

object SeedButtonDefaults {
    object Colors {
        @Composable
        fun containerPrimary(
            backgroundColor: Color = SeedTheme.colorScheme.container.primary,
            contentColor: Color = SeedTheme.colorScheme.contents.onPrimary
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun outlinePrimary(
            backgroundColor: Color = SeedTheme.colorScheme.container.neutralPrimary,
            contentColor: Color = SeedTheme.colorScheme.container.primary,
            borderColor: Color = SeedTheme.colorScheme.container.primary,
            disabledBorderColor: Color = SeedTheme.colorScheme.container.neutralTertiary.copy(
                alpha = 0.6f
            )
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            borderColor = borderColor,
            disabledBorderColor = disabledBorderColor
        )

        @Composable
        fun tintPrimary(
            backgroundColor: Color = SeedTheme.colorScheme.container.secondary,
            contentColor: Color = SeedTheme.colorScheme.container.primary
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun outlineNeutral(
            backgroundColor: Color = SeedTheme.colorScheme.container.neutralPrimary,
            contentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            borderColor: Color = SeedTheme.colorScheme.container.outline,
            disabledBorderColor: Color = SeedTheme.colorScheme.contents.neutralTertiary.copy(
                alpha = 0.6f
            )
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            borderColor = borderColor,
            disabledBorderColor = disabledBorderColor
        )

        @Composable
        fun tintNeutral(
            backgroundColor: Color = SeedTheme.colorScheme.contents.neutralSecondary,
            contentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun containerNegative(
            backgroundColor: Color = SeedTheme.colorScheme.container.error,
            contentColor: Color = SeedTheme.colorScheme.contents.onPrimary
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun tintNegative(
            backgroundColor: Color = SeedTheme.colorScheme.container.error,
            contentColor: Color = SeedTheme.colorScheme.contents.error
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    }

    @Composable
    fun buttonColors(
        backgroundColor: Color,
        contentColor: Color,
        disabledBackgroundColor: Color =
            SeedTheme.colorScheme.container.neutralSecondary.copy(alpha = 0.6f),
        disabledContentColor: Color =
            SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = 0.6f),
        borderColor: Color = Color.Transparent,
        disabledBorderColor: Color = Color.Transparent,
    ): SeedButton.Colors =
        SeedButtonColors(
            backgroundColor,
            contentColor,
            disabledBackgroundColor,
            disabledContentColor,
            borderColor,
            disabledBorderColor
        )
}

@Immutable
private data class SeedButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color =
        SeedColorScheme().container.neutralSecondary.copy(alpha = 0.6f),
    private val disabledContentColor: Color =
        SeedColorScheme().contents.neutralPrimary.copy(alpha = 0.6f),
    private val borderColor: Color = Color.Transparent,
    private val disabledBorderColor: Color = Color.Transparent,
) : SeedButton.Colors {
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

@Preview(widthDp = 250, heightDp = 470)
@Composable
fun DisabledButtonPreview() {
    Surface {

        Column(verticalArrangement = Arrangement.SpaceBetween) {
            SeedButton(
                text = "DISABLED",
                height = SeedButton.Height.Large,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            ) {}

            SeedButton(
                text = "ENABLED",
                height = SeedButton.Height.Large,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {}
        }



    }
}


