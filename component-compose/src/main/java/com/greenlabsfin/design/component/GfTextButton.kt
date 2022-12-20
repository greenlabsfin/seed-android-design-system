package com.greenlabsfin.design.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.LocalGfTypoScheme

@Composable
fun GfTextButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Count? = null,
    badge: Boolean = false,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalGfTypoScheme.current.body.mediumBold,
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



    Surface(
        color = surfaceColor.value,
        shape = RoundedCornerShape(2.dp)
    ) {
        GfText(
            modifier = modifier.clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            ),
            text = text,
            leftIcon = leftIcon,
            rightIcon = rightIcon,
            count = count,
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

object GfTextButton {
    object Style {
        val XSmall: TextStyle
            @Composable
            get() = GfTheme.typoScheme.caption.xSmallMedium
        val Small: TextStyle
            @Composable
            get() = GfTheme.typoScheme.body.smallMedium
        val Medium: TextStyle
            @Composable
            get() = GfTheme.typoScheme.body.mediumMedium
    }
}

@Preview(showBackground = true)
@Composable
fun GfTextButtonPreview() {
    GfTheme {
        Surface(
            color = GfTheme.colorScheme.container.background
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GfTextButton(
                    text = "활성화 XS 버튼",
                    color = GfTheme.colorScheme.contents.primary,
                    rightIcon = Icons.Filled.ArrowDropDown,
                    style = GfTextButton.Style.XSmall,
                ) {}

                GfTextButton(
                    text = "비활성화 XS 버튼",
                    color = GfTheme.colorScheme.contents.primary,
                    rightIcon = Icons.Filled.ArrowDropDown,
                    style = GfTextButton.Style.XSmall,
                    enabled = false
                ) {}

                GfTextButton(
                    text = "활성화 SM 버튼",
                    color = GfTheme.colorScheme.contents.neutralSecondary,
                    rightIcon = Icons.Filled.KeyboardArrowRight,
                    style = GfTextButton.Style.Small,
                ) {}

                GfTextButton(
                    text = "비활성화 SM 버튼",
                    color = GfTheme.colorScheme.contents.neutralSecondary,
                    rightIcon = Icons.Filled.KeyboardArrowRight,
                    style = GfTextButton.Style.Small,
                    enabled = false
                ) {}

                GfTextButton(
                    text = "활성화 MD 버튼",
                    color = GfTheme.colorScheme.contents.primary,
                    rightIcon = Icons.Filled.ArrowDropDown,
                    style = GfTextButton.Style.Medium,
                ) {}

                GfTextButton(
                    text = "비활성화 MD 버튼",
                    color = GfTheme.colorScheme.contents.primary,
                    rightIcon = Icons.Filled.ArrowDropDown,
                    style = GfTextButton.Style.Medium,
                    enabled = false
                ) {}
            }
        }
    }
}
