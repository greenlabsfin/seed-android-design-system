package com.greenlabsfin.design.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.gray90

object SeedChipDefaults {
    val minWidth = 56.dp

    object Colors {

        @Composable
        fun primary(
            backgroundColor: Color = SeedTheme.colorScheme.container.primary,
            disabledBackgroundColor: Color = SeedTheme.colorScheme.container.neutralSecondary,
            textColor: Color = SeedTheme.colorScheme.contents.onPrimary,
            disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            leadingIconColor: Color = SeedTheme.colorScheme.contents.onPrimary,
            disabledLeadingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor: Color = SeedTheme.colorScheme.contents.onPrimary,
            disabledTrailingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor: Color = gray90.copy(alpha = .24f),
            countColors: SeedCount.Colors = SeedCountDefaults.Colors.secondary(),
            countTextColor: Color = SeedTheme.colorScheme.contents.onPrimary,
            disabledCountTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        ): SeedChip.Colors = chipColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            leadingIconColor = leadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            trailingIconColor = trailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            pressedColor = pressedColor,
            countColors = countColors,
            countTextColor = countTextColor,
            disabledCountTextColor = disabledCountTextColor
        )

        @Composable
        fun neutral(
            backgroundColor: Color = SeedTheme.colorScheme.container.neutralSecondary,
            disabledBackgroundColor: Color = SeedTheme.colorScheme.container.neutralTertiary,
            textColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            leadingIconColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledLeadingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTrailingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor: Color = SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
            countColors: SeedCount.Colors = SeedCountDefaults.Colors.neutral(),
            countTextColor: Color = SeedTheme.colorScheme.contents.primary,
            disabledCountTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        ): SeedChip.Colors = chipColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            leadingIconColor = leadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            trailingIconColor = trailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            pressedColor = pressedColor,
            countColors = countColors,
            countTextColor = countTextColor,
            disabledCountTextColor = disabledCountTextColor
        )

        @Composable
        fun primaryOutline(
            backgroundColor: Color = SeedTheme.colorScheme.container.secondary,
            disabledBackgroundColor: Color = SeedTheme.colorScheme.container.neutralSecondary,
            textColor: Color = SeedTheme.colorScheme.contents.primary,
            disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            outlineColor: Color = SeedTheme.colorScheme.container.primary,
            disabledOutlineColor: Color = SeedTheme.colorScheme.container.neutralTertiary,
            leadingIconColor: Color = SeedTheme.colorScheme.contents.primary,
            disabledLeadingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor: Color = SeedTheme.colorScheme.contents.primary,
            disabledTrailingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor: Color = SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
            countColors: SeedCount.Colors = SeedCountDefaults.Colors.primary(),
            countTextColor: Color = SeedTheme.colorScheme.contents.primary,
            disabledCountTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        ): SeedChip.Colors = chipColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            outlineColor = outlineColor,
            disabledOutlineColor = disabledOutlineColor,
            leadingIconColor = leadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            trailingIconColor = trailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            pressedColor = pressedColor,
            countColors = countColors,
            countTextColor = countTextColor,
            disabledCountTextColor = disabledCountTextColor,
        )

        @Composable
        fun neutralOutline(
            backgroundColor: Color = SeedTheme.colorScheme.container.background,
            disabledBackgroundColor: Color = SeedTheme.colorScheme.container.neutralSecondary,
            textColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            outlineColor: Color = SeedTheme.colorScheme.container.outline,
            disabledOutlineColor: Color = SeedTheme.colorScheme.container.neutralTertiary,
            leadingIconColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledLeadingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTrailingIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor: Color = SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
            countColors: SeedCount.Colors = SeedCountDefaults.Colors.neutral(),
            countTextColor: Color = SeedTheme.colorScheme.contents.primary,
            disabledCountTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        ): SeedChip.Colors = chipColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            outlineColor = outlineColor,
            disabledOutlineColor = disabledOutlineColor,
            leadingIconColor = leadingIconColor,
            disabledLeadingIconColor = disabledLeadingIconColor,
            trailingIconColor = trailingIconColor,
            disabledTrailingIconColor = disabledTrailingIconColor,
            pressedColor = pressedColor,
            countColors = countColors,
            countTextColor = countTextColor,
            disabledCountTextColor = disabledCountTextColor,
        )
    }

    @Composable
    fun chipColors(
        backgroundColor: Color = Color.Unspecified,
        disabledBackgroundColor: Color = Color.Unspecified,
        textColor: Color = Color.Unspecified,
        disabledTextColor: Color = Color.Unspecified,
        outlineColor: Color = Color.Unspecified,
        disabledOutlineColor: Color = Color.Unspecified,
        leadingIconColor: Color = Color.Unspecified,
        disabledLeadingIconColor: Color = Color.Unspecified,
        trailingIconColor: Color = Color.Unspecified,
        disabledTrailingIconColor: Color = Color.Unspecified,
        pressedColor: Color = Color.Unspecified,
        countColors: SeedCount.Colors,
        countTextColor: Color = Color.Unspecified,
        disabledCountTextColor: Color = Color.Unspecified,
    ): SeedChip.Colors = DefaultSeedChipColors(
        backgroundColor = backgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        textColor = textColor,
        disabledTextColor = disabledTextColor,
        outlineColor = outlineColor,
        disabledOutlineColor = disabledOutlineColor,
        leadingIconColor = leadingIconColor,
        disabledLeadingIconColor = disabledLeadingIconColor,
        trailingIconColor = trailingIconColor,
        disabledTrailingIconColor = disabledTrailingIconColor,
        pressedColor = pressedColor,
        countColors = countColors,
        countTextColor = countTextColor,
        disabledCountTextColor = disabledCountTextColor,
    )
}

@Immutable
private data class DefaultSeedChipColors(
    private val backgroundColor: Color,
    private val disabledBackgroundColor: Color,
    private val textColor: Color,
    private val disabledTextColor: Color,
    private val outlineColor: Color,
    private val disabledOutlineColor: Color,
    private val leadingIconColor: Color,
    private val disabledLeadingIconColor: Color,
    private val trailingIconColor: Color,
    private val disabledTrailingIconColor: Color,
    private val countTextColor: Color,
    private val disabledCountTextColor: Color,
    override val pressedColor: Color,
    override val countColors: SeedCount.Colors,
) : SeedChip.Colors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)

    @Composable
    override fun textColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) textColor else disabledTextColor)

    @Composable
    override fun outlineColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) outlineColor else disabledOutlineColor)

    @Composable
    override fun leadingIconColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) leadingIconColor else disabledLeadingIconColor)

    @Composable
    override fun trailingIconColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) trailingIconColor else disabledTrailingIconColor)

    @Composable
    override fun countTextColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) countTextColor else disabledCountTextColor)

}

object SeedChip {
    enum class Size(val height: Dp) {
        Large(48.dp),
        Medium(40.dp),
        Small(32.dp);

        val iconSize: Dp
            @Composable
            get() = when (this) {
                Large -> 20.dp
                else -> 16.dp
            }

        val imageSize: Dp
            @Composable
            get() = height.minus(8.dp)


    }

    enum class Style {
        Pill,
        Rectangle;

        fun radius(size: Size): Dp {
            return when (this) {
                Pill -> when (size) {
                    Size.Large -> 24.dp
                    Size.Medium -> 20.dp
                    Size.Small -> 16.dp
                }
                Rectangle -> when (size) {
                    Size.Large -> 12.dp
                    Size.Medium -> 8.dp
                    Size.Small -> 8.dp
                }
            }
        }

        fun startPadding(size: Size, hasImage: Boolean): Dp {
            return if (hasImage) 4.dp
            else when (this) {
                Pill -> when (size) {
                    Size.Large -> 12.dp
                    Size.Medium -> 10.dp
                    Size.Small -> 8.dp
                }
                Rectangle -> 8.dp
            }
        }

        fun endPadding(size: Size): Dp =
            when (this) {
                Pill -> when (size) {
                    Size.Large -> 12.dp
                    Size.Medium -> 10.dp
                    Size.Small -> 8.dp
                }
                Rectangle -> 8.dp
            }
    }

    @Stable
    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean): State<Color>

        @Composable
        fun textColor(enabled: Boolean): State<Color>

        @Composable
        fun outlineColor(enabled: Boolean): State<Color>

        @Composable
        fun leadingIconColor(enabled: Boolean): State<Color>

        @Composable
        fun trailingIconColor(enabled: Boolean): State<Color>

        @Composable
        fun countTextColor(enabled: Boolean): State<Color>

        val countColors: SeedCount.Colors

        val pressedColor: Color
    }
}

@Composable
fun SeedChip(
    modifier: Modifier = Modifier,
    size: SeedChip.Size,
    style: SeedChip.Style,
    text: String? = null,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumMedium,
    leadingImagePainter: Painter? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    count: Int? = null,
    isCircleCount: Boolean = true,
    colors: SeedChip.Colors = SeedChipDefaults.Colors.primary(),
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    if (leadingImagePainter != null && leadingIcon != null) throw IllegalArgumentException("only one leading argument allowed")

    val isImageOnly = text == null && count == null && trailingIcon == null
    val shape = when (style) {
        SeedChip.Style.Pill ->
            if (isImageOnly) CircleShape
            else RoundedCornerShape(style.radius(size))
        SeedChip.Style.Rectangle -> RoundedCornerShape(style.radius(size))
    }


    val borderStroke =
        colors.outlineColor(enabled = enabled).value
            .takeIf { it.isUnspecified.not() }
            ?.let { BorderStroke(width = 1.dp, color = it) }

    val interactionSource = remember { MutableInteractionSource() }
    val backgroundColor = colors.backgroundColor(enabled = enabled).value
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    val surfaceColor = remember { Animatable(colors.pressedColor) }
    when {
        isHovered.value -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(colors.pressedColor.compositeOver(backgroundColor))
            }
        }
        isPressed.value -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(colors.pressedColor.compositeOver(backgroundColor))
            }
        }
        else -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(backgroundColor)
            }
        }
    }


    Surface(
        modifier = modifier
            .defaultMinSize(
                minHeight = size.height,
                minWidth = if (shape == CircleShape) size.height else SeedChipDefaults.minWidth
            )
            .height(size.height),
        color = surfaceColor.value,
        shape = shape,
        border = borderStroke
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(
                    start = style.startPadding(size, leadingImagePainter != null),
                    end = style.endPadding(size),
                )
                .then(
                    onClick?.let {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            enabled = enabled,
                            onClick = it,
                        )
                    } ?: Modifier
                )
        ) {
            leadingImagePainter?.let {
                Box(modifier = Modifier
                    .size(size.imageSize)
                    .clip(CircleShape)) {
                    Image(
                        modifier = modifier.size(40.dp),
                        painter = it,
                        contentDescription = ""
                    )
                }
            }
            leadingIcon?.let {
                SeedIcon(
                    modifier = Modifier.size(size.iconSize),
                    imageVector = it,
                    contentDescription = it.name,
                    tint = colors.leadingIconColor(enabled = enabled).value
                )
            }
            text?.let {
                when {
                    leadingImagePainter != null -> Spacer(modifier = Modifier.width(8.dp))
                    leadingIcon != null -> Spacer(modifier = Modifier.width(4.dp))
                }
                Box(modifier = modifier.weight(1f, false)) {
                    SeedText(
                        text = it,
                        style = textStyle,
                        color = colors.textColor(enabled = enabled).value,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
            count?.let {
                Spacer(modifier = Modifier.width(2.dp))
                if (isCircleCount) {
                    SeedCount(
                        modifier = Modifier.size(20.dp),
                        count = it,
                        colors = colors.countColors)
                } else {
                    SeedText(
                        text = it.toString(),
                        color = colors.countTextColor(enabled = enabled).value,
                        style = textStyle,
                    )
                }
            }
            trailingIcon?.let {
                Spacer(modifier = Modifier.width(4.dp))
                SeedIcon(
                    modifier = Modifier.size(size.iconSize),
                    imageVector = it,
                    contentDescription = it.name,
                    tint = colors.leadingIconColor(enabled = enabled).value
                )
            }
        }
    }
}
