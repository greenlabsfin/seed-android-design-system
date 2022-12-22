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
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray90

object GfChipDefaults {
    val minWidth = 56.dp

    object Colors {
        val primary: GfChipColors
            @Composable
            get() = chipColors(
                backgroundColor = GfTheme.colorScheme.container.primary,
                disabledBackgroundColor = GfTheme.colorScheme.container.neutralSecondary,
                textColor = GfTheme.colorScheme.contents.onPrimary,
                disabledTextColor = GfTheme.colorScheme.contents.neutralTertiary,
                leadingIconColor = GfTheme.colorScheme.contents.onPrimary,
                disabledLeadingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                trailingIconColor = GfTheme.colorScheme.contents.onPrimary,
                disabledTrailingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                pressedColor = gray90.copy(alpha = .24f),
                countColors = GfCountDefaults.Colors.secondary,
                countTextColor = GfTheme.colorScheme.contents.onPrimary,
                disabledCountTextColor = GfTheme.colorScheme.contents.neutralTertiary,
            )

        val neutral: GfChipColors
            @Composable
            get() = chipColors(
                backgroundColor = GfTheme.colorScheme.container.neutralSecondary,
                disabledBackgroundColor = GfTheme.colorScheme.container.neutralTertiary,
                textColor = GfTheme.colorScheme.contents.neutralPrimary,
                disabledTextColor = GfTheme.colorScheme.contents.neutralTertiary,
                leadingIconColor = GfTheme.colorScheme.contents.neutralPrimary,
                disabledLeadingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                trailingIconColor = GfTheme.colorScheme.contents.neutralPrimary,
                disabledTrailingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                pressedColor = GfTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
                countColors = GfCountDefaults.Colors.neutral,
                countTextColor = GfTheme.colorScheme.contents.primary,
                disabledCountTextColor = GfTheme.colorScheme.contents.neutralTertiary,
            )

        val primaryOutline: GfChipColors
            @Composable
            get() = chipColors(
                backgroundColor = GfTheme.colorScheme.container.secondary,
                disabledBackgroundColor = GfTheme.colorScheme.container.neutralSecondary,
                textColor = GfTheme.colorScheme.contents.primary,
                disabledTextColor = GfTheme.colorScheme.contents.neutralTertiary,
                outlineColor = GfTheme.colorScheme.container.primary,
                disabledOutlineColor = GfTheme.colorScheme.container.neutralTertiary,
                leadingIconColor = GfTheme.colorScheme.contents.primary,
                disabledLeadingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                trailingIconColor = GfTheme.colorScheme.contents.primary,
                disabledTrailingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                pressedColor = GfTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
                countColors = GfCountDefaults.Colors.primary,
                countTextColor = GfTheme.colorScheme.contents.primary,
                disabledCountTextColor = GfTheme.colorScheme.contents.neutralTertiary,
            )

        val neutralOutline: GfChipColors
            @Composable
            get() = chipColors(
                backgroundColor = GfTheme.colorScheme.container.background,
                disabledBackgroundColor = GfTheme.colorScheme.container.neutralSecondary,
                textColor = GfTheme.colorScheme.contents.neutralPrimary,
                disabledTextColor = GfTheme.colorScheme.contents.neutralTertiary,
                outlineColor = GfTheme.colorScheme.container.outline,
                disabledOutlineColor = GfTheme.colorScheme.container.neutralTertiary,
                leadingIconColor = GfTheme.colorScheme.contents.neutralPrimary,
                disabledLeadingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                trailingIconColor = GfTheme.colorScheme.contents.neutralPrimary,
                disabledTrailingIconColor = GfTheme.colorScheme.contents.neutralTertiary,
                pressedColor = GfTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
                countColors = GfCountDefaults.Colors.neutral,
                countTextColor = GfTheme.colorScheme.contents.primary,
                disabledCountTextColor = GfTheme.colorScheme.contents.neutralTertiary,
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
        countColors: GfCountColors,
        countTextColor: Color = Color.Unspecified,
        disabledCountTextColor: Color = Color.Unspecified,
    ): GfChipColors = DefaultGfChipColors(
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

@Stable
interface GfChipColors {
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

    val countColors: GfCountColors

    val pressedColor: Color
}

@Immutable
private data class DefaultGfChipColors(
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
    override val countColors: GfCountColors,
) : GfChipColors {
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

object GfChip {
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
}

@Composable
fun GfChip(
    modifier: Modifier = Modifier,
    size: GfChip.Size,
    style: GfChip.Style,
    text: String? = null,
    textStyle: TextStyle = GfTheme.typoScheme.body.mediumMedium,
    leadingImagePainter: Painter? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    count: Int? = null,
    isCircleCount: Boolean = true,
    colors: GfChipColors = GfChipDefaults.Colors.primary,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
) {
    if (leadingImagePainter != null && leadingIcon != null) throw IllegalArgumentException("only one leading argument allowed")

    val isImageOnly = text == null && count == null && trailingIcon == null
    val shape = when (style) {
        GfChip.Style.Pill ->
            if (isImageOnly) CircleShape
            else RoundedCornerShape(style.radius(size))
        GfChip.Style.Rectangle -> RoundedCornerShape(style.radius(size))
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
                minWidth = if (shape == CircleShape) size.height else GfChipDefaults.minWidth
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
                GfIcon(
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
                    GfText(
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
                    GfCount(
                        modifier = Modifier.size(20.dp),
                        count = it,
                        colors = colors.countColors)
                } else {
                    GfText(
                        text = it.toString(),
                        color = colors.countTextColor(enabled = enabled).value,
                        style = textStyle,
                    )
                }
            }
            trailingIcon?.let {
                Spacer(modifier = Modifier.width(4.dp))
                GfIcon(
                    modifier = Modifier.size(size.iconSize),
                    imageVector = it,
                    contentDescription = it.name,
                    tint = colors.leadingIconColor(enabled = enabled).value
                )
            }
        }
    }
}
