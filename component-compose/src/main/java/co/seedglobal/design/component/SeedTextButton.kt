package co.seedglobal.design.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.SeedTheme

@Composable
fun SeedTextButton(
    modifier: Modifier = Modifier,
    text: String,
    leadingIcon: Painter? = null,
    leadingIconContentDescription: String? = null,
    leadingIconSize: Dp = 16.dp,
    leadingIconColor: Color = Color.Unspecified,
    trailingIcon: Painter? = null,
    trailingIconContentDescription: String? = null,
    trailingIconSize: Dp = 16.dp,
    trailingIconColor: Color = Color.Unspecified,
    count: Int? = null,
    countColors: SeedCountColors? = null,
    badge: Boolean = false,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = SeedTextButtonDefaults.Medium,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()
    val isHovered = interactionSource.collectIsHoveredAsState()
    val surfaceColor = remember { Animatable(Color.Transparent) }
    when {
        isHovered.value -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(color.copy(alpha = .04f))
            }
        }
        isPressed.value -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(color.copy(alpha = .08f))
            }
        }
        else -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(Color.Transparent)
            }
        }
    }

    SeedSurface(
        color = surfaceColor.value,
        shape = RoundedCornerShape(2.dp)
    ) {
        SeedText(
            modifier = modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            ),
            text = text,
            leadingIcon = leadingIcon,
            leadingIconContentDescription = leadingIconContentDescription,
            leadingIconColor = leadingIconColor,
            leadingIconSize = leadingIconSize,
            trailingIcon = trailingIcon,
            trailingIconContentDescription = trailingIconContentDescription,
            trailingIconSize = trailingIconSize,
            trailingIconColor = trailingIconColor,
            count = count,
            countColors = countColors,
            badge = badge,
            color = color,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            onTextLayout = onTextLayout,
            style = style,
            enabled = enabled,
        )
    }
}

object SeedTextButtonDefaults {
    val XSmall: TextStyle
        @Composable
        get() = SeedTheme.typoScheme.caption.xSmallMedium
    val Small: TextStyle
        @Composable
        get() = SeedTheme.typoScheme.body.smallMedium
    val Medium: TextStyle
        @Composable
        get() = SeedTheme.typoScheme.body.mediumMedium
}
