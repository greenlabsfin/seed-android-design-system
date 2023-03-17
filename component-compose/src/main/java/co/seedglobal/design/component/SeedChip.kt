package co.seedglobal.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isUnspecified
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.gray90

@Composable
fun SeedChip(
    size: SeedChip.Size,
    style: SeedChip.Style,
    colors: SeedChipColors,
    modifier: Modifier = Modifier,
    text: String? = null,
    textAlign: TextAlign = TextAlign.Start,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumMedium,
    leadingImagePainter: Painter? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    count: Int? = null,
    isCircleCount: Boolean = true,
    enabled: Boolean = true,
    clickable: Boolean = true,
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

    SeedSurface(
        modifier = modifier
            .defaultMinSize(
                minHeight = size.height,
                minWidth = if (shape == CircleShape) size.height else 0.dp
            )
            .height(size.height),
        color = backgroundColor,
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
                            enabled = enabled && clickable,
                            onClick = it,
                        )
                    } ?: Modifier
                )
        ) {
            leadingImagePainter?.let {
                Box(
                    modifier = Modifier
                        .size(size.imageSize)
                        .clip(CircleShape)
                ) {
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
                        textAlign = textAlign,
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
                        colors = colors.countColors
                    )
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

object SeedChipDefaults {
    val minWidth = 56.dp

    @Composable
    fun primaryColors(): SeedChipColors =
        chipColors(
            backgroundColor = SeedTheme.colorScheme.container.primary,
            disabledBackgroundColor = SeedTheme.colorScheme.container.neutralSecondary,
            textColor = SeedTheme.colorScheme.contents.onPrimary,
            disabledTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
            leadingIconColor = SeedTheme.colorScheme.contents.onPrimary,
            disabledLeadingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor = SeedTheme.colorScheme.contents.onPrimary,
            disabledTrailingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor = gray90.copy(alpha = .24f),
            countColors = SeedCountDefaults.secondaryColors(),
            countTextColor = SeedTheme.colorScheme.contents.onPrimary,
            disabledCountTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
        )

    @Composable
    fun neutralColors(): SeedChipColors =
        chipColors(
            backgroundColor = SeedTheme.colorScheme.container.neutralSecondary,
            disabledBackgroundColor = SeedTheme.colorScheme.container.neutralTertiary,
            textColor = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
            leadingIconColor = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledLeadingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTrailingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor = SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
            countColors = SeedCountDefaults.neutralColors(),
            countTextColor = SeedTheme.colorScheme.contents.primary,
            disabledCountTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
        )

    @Composable
    fun primaryOutlineColors(): SeedChipColors =
        chipColors(
            backgroundColor = SeedTheme.colorScheme.container.secondary,
            disabledBackgroundColor = SeedTheme.colorScheme.container.neutralSecondary,
            textColor = SeedTheme.colorScheme.contents.primary,
            disabledTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
            outlineColor = SeedTheme.colorScheme.container.primary,
            disabledOutlineColor = SeedTheme.colorScheme.container.neutralTertiary,
            leadingIconColor = SeedTheme.colorScheme.contents.primary,
            disabledLeadingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor = SeedTheme.colorScheme.contents.primary,
            disabledTrailingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor = SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
            countColors = SeedCountDefaults.primaryColors(),
            countTextColor = SeedTheme.colorScheme.contents.primary,
            disabledCountTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
        )

    @Composable
    fun neutralOutlineColors(): SeedChipColors =
        chipColors(
            backgroundColor = SeedTheme.colorScheme.container.background,
            disabledBackgroundColor = SeedTheme.colorScheme.container.neutralSecondary,
            textColor = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
            outlineColor = SeedTheme.colorScheme.container.outline,
            disabledOutlineColor = SeedTheme.colorScheme.container.neutralTertiary,
            leadingIconColor = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledLeadingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            trailingIconColor = SeedTheme.colorScheme.contents.neutralPrimary,
            disabledTrailingIconColor = SeedTheme.colorScheme.contents.neutralTertiary,
            pressedColor = SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = .08f),
            countColors = SeedCountDefaults.neutralColors(),
            countTextColor = SeedTheme.colorScheme.contents.primary,
            disabledCountTextColor = SeedTheme.colorScheme.contents.neutralTertiary,
        )

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
        countColors: SeedCountColors = SeedCountDefaults.countColors(),
        countTextColor: Color = Color.Unspecified,
        disabledCountTextColor: Color = Color.Unspecified,
    ): SeedChipColors = DefaultSeedChipColors(
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
interface SeedChipColors {
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

    val countColors: SeedCountColors

    val pressedColor: Color
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
    override val countColors: SeedCountColors,
) : SeedChipColors {
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
}
