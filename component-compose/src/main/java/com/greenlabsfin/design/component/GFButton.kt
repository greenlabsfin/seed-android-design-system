package com.greenlabsfin.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.color.GfColorScheme

enum class GFHeight(val displayPixel: Dp) {
    XLarge(64.dp),
    Large(56.dp),
    Medium(48.dp),
    Small(40.dp),
    XSmall(32.dp)
}

enum class GFButtonType {
    Outlined,
    Tinted,
    Normal
}

enum class GFButtonStyle {
    Primary,
    Neutral,
    Error
}

@Composable
fun GFButton(
    type: GFButtonType,
    height: GFHeight,
    style: GFButtonStyle,
    onClick: () -> Unit,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
) {
    val radius = when (height) {
        GFHeight.XLarge, GFHeight.Large -> 12.dp
        GFHeight.Medium -> 10.dp
        GFHeight.Small, GFHeight.XSmall -> 8.dp
    }
    when (type) {
        GFButtonType.Outlined -> GFOutlinedButton(height.displayPixel,
            radius,
            onClick,
            style,
            text,
            leftIcon,
            rightIcon,
            count)
        GFButtonType.Tinted -> GFTintedButton(height.displayPixel,
            radius,
            onClick,
            style,
            text,
            leftIcon,
            rightIcon,
            count)
        GFButtonType.Normal -> GFContainerButton(height.displayPixel,
            radius,
            onClick,
            style,
            text,
            leftIcon,
            rightIcon,
            count)
    }
}

@Composable
private fun GFOutlinedButton(
    height: Dp,
    radius: Dp,
    onClick: () -> Unit,
    gfButtonStyle: GFButtonStyle,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
) {
    val colorScheme = GfColorScheme()
    OutlinedButton(
        contentPadding = PaddingValues(horizontal = 12.dp),
        modifier = Modifier.height(height),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = colorScheme.container.background,
            contentColor = when (gfButtonStyle) {
                GFButtonStyle.Primary -> colorScheme.contents.primary
                else -> colorScheme.contents.neutralSecondary
            },
            disabledContentColor = colorScheme.contents.neutralPrimary
        ),
        border = BorderStroke(1.dp, when (gfButtonStyle) {
            GFButtonStyle.Primary -> colorScheme.container.primary
            else -> colorScheme.container.outline
        }),
        shape = RoundedCornerShape(radius)

    ) {
        leftIcon?.let {
            Icon(it, "Description")
        }
        text?.let {
            Text(it)
        }
        rightIcon?.let {
            Icon(it, "Right Icon")
        }
        count?.let {
            GFButtonCountComponent(it)
        }
    }
}

@Composable
private fun GFButtonCountComponent(
    count: Int = 0,
) {

}

@Composable
private fun GFTintedButton(
    height: Dp,
    radius: Dp,
    onClick: () -> Unit,
    gfButtonStyle: GFButtonStyle,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
) {
    val colorScheme = GfColorScheme()
    Button(
        contentPadding = PaddingValues(horizontal = 12.dp),
        modifier = Modifier.height(height),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (gfButtonStyle) {
                GFButtonStyle.Primary -> colorScheme.container.secondary
                GFButtonStyle.Neutral -> colorScheme.container.neutralSecondary
                GFButtonStyle.Error -> colorScheme.container.error
            },
            contentColor = when (gfButtonStyle) {
                GFButtonStyle.Primary -> colorScheme.contents.primary
                GFButtonStyle.Neutral -> colorScheme.contents.neutralPrimary
                GFButtonStyle.Error -> colorScheme.contents.error
            },
            disabledBackgroundColor = colorScheme.container.neutralSecondary,
            disabledContentColor = colorScheme.contents.neutralPrimary
        ),
        shape = RoundedCornerShape(radius)
    ) {
        leftIcon?.let {
            Icon(it, "Description")
        }
        text?.let {
            Text(it)
        }
        rightIcon?.let {
            Icon(it, "Right Icon")
        }
        count?.let {
            GFButtonCountComponent(it)
        }
    }
}

@Composable
private fun GFContainerButton(
    height: Dp,
    radius: Dp,
    onClick: () -> Unit,
    gfButtonStyle: GFButtonStyle,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
) {
    val colorScheme = GfColorScheme()
    Button(
        contentPadding = PaddingValues(horizontal = 12.dp),
        modifier = Modifier.height(height),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = when (gfButtonStyle) {
                GFButtonStyle.Error -> colorScheme.contents.error
                else -> colorScheme.contents.primary
            },
            contentColor = colorScheme.contents.onPrimary,
            disabledBackgroundColor = colorScheme.container.neutralSecondary,
            disabledContentColor = colorScheme.contents.neutralPrimary
        ),
        shape = RoundedCornerShape(radius)
    ) {
        leftIcon?.let {
            Icon(it, "Description")
        }
        text?.let {
            Text(it)
        }
        rightIcon?.let {
            Icon(it, "Right Icon")
        }
        count?.let {
            GFButtonCountComponent(it)
        }
    }
}

@Preview("PrimaryButtonMedium")
@Composable
fun PrimaryButtonPreview() {
    Surface {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            GFButton(type = GFButtonType.Outlined, height = GFHeight.Medium, style = GFButtonStyle.Primary, onClick = {}, text = "Test Text")
            GFButton(type = GFButtonType.Tinted, height = GFHeight.Medium, style = GFButtonStyle.Primary, onClick = {}, text = "Test Text")
            GFButton(type = GFButtonType.Normal, height = GFHeight.Medium, style = GFButtonStyle.Primary, onClick = {}, text = "Test Text")
        }
    }
}

@Preview("PrimaryButtonLarge")
@Composable
fun LargePrimaryButtonPreview() {
    Surface {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            GFButton(type = GFButtonType.Outlined,
                height = GFHeight.XLarge,
                style = GFButtonStyle.Primary,
                onClick = {},
                leftIcon = Icons.Filled.Home,
                text = "Test Text"
            )

            GFButton(type = GFButtonType.Tinted,
                height = GFHeight.XLarge,
                style = GFButtonStyle.Primary,
                text = "Test Text",
                onClick = {})

            GFButton(type = GFButtonType.Normal,
                height = GFHeight.XLarge,
                style = GFButtonStyle.Primary,
                text = "Test Text",
                rightIcon = Icons.Filled.AccountCircle,
                onClick = {})
        }
    }
}

@Preview("ErrorButtonMedium")
@Composable
fun ErrorButtonMedium() {
    Surface {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            GFButton(type = GFButtonType.Tinted, height = GFHeight.Medium, style = GFButtonStyle.Error, onClick = {}, text = "Test Text")
            GFButton(type = GFButtonType.Normal, height = GFHeight.Medium, style = GFButtonStyle.Error, onClick = {}, text = "Test Text")
        }
    }
}