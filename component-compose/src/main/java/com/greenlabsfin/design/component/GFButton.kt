package com.greenlabsfin.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray60
import com.greenlabsfin.design.core.color.red80

enum class GFHeight(val displayPixel: Dp) {
    XLarge(64.dp),
    Large(56.dp),
    Medium(48.dp),
    Small(40.dp),
    XSmall(32.dp);

    val typography: TextStyle
        @Composable
        get() = when (this) {
            XLarge -> GfTheme.typoScheme.body.xLargeBold
            Large -> GfTheme.typoScheme.body.largeBold
            Medium -> GfTheme.typoScheme.body.mediumMedium
            else -> GfTheme.typoScheme.body.smallMedium
        }

    val radius: Dp
        get() = when (this) {
            XLarge, Large -> 12.dp
            Medium -> 10.dp
            Small, XSmall -> 8.dp
        }
}

@Composable
fun GFButton(
    height: GFHeight,
    colors: GFButtonColor,
    enabled: Boolean = true,
    text: String? = null,
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector? = null,
    count: Int? = null,
    countStyle: GfCountStyle? = null,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.height(height.displayPixel),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors.backgroundColor(enabled = enabled).value,
            contentColor = colors.contentColor(enabled = enabled).value
        ),
        border = BorderStroke(1.dp, colors.borderColor(enabled = enabled).value),
        contentPadding = PaddingValues(horizontal = 12.dp),
        shape = RoundedCornerShape(height.radius)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically) {
            leftIcon?.let {
                Icon(imageVector = it, contentDescription = "GFButton LeftIcon")
            }
            text?.let {
                Text(text = it, style = height.typography)
            }
            count?.let {
                countStyle?.let {
                    GfCount(
                        count = count,
                        style = countStyle,
                        enabled = enabled,
                    )
                }
            }
            rightIcon?.let {
                Icon(imageVector = it, contentDescription = "GFButton RightIcon")
            }
        }
    }
}

@Preview("PrimaryButtons")
@Composable
fun PrimaryButtonPreview() {
    Surface {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)) {
            Text(text = "Primary", style = GfTheme.typoScheme.headline.largeBold)
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.containerPrimary,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward,
                count = 2,
                countStyle = GfCount.Style.getDefault(buttonColor = GFButton.Style.containerPrimary)
            )
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.outlinePrimary,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward,
                count = 2,
                countStyle = GfCount.Style.getDefault(buttonColor = GFButton.Style.outlinePrimary)
            )
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.tintPrimary,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward,
                count = 22,
                countStyle = GfCount.Style.getDefault(buttonColor = GFButton.Style.tintPrimary)
            )
        }
    }
}

@Preview("NeutralButtons")
@Composable
fun NeutralButtonPreview() {
    Surface {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)) {
            Text(text = "Neutral", style = GfTheme.typoScheme.headline.largeBold)
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.outlineNeutral,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward
            )
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.tintNeutral,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward
            )
        }
    }
}

@Preview("negativeButtons")
@Composable
fun NegativeButtonPreview() {
    Surface {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(16.dp)) {
            Text(text = "Negative", style = GfTheme.typoScheme.headline.largeBold)
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.containerNegative,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward
            )
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.tintNegative,
                onClick = {},
                leftIcon = Icons.Outlined.Notifications,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowForward
            )
        }
    }
}

@Preview("CustomButtons")
@Composable
fun CustomButtonPreview() {
    Surface {
        Column(modifier = Modifier.padding(16.dp)) {
            GFButton(
                height = GFHeight.Small,
                colors = GFButton.Style.custom(backgroundColor = red80, contentColor = gray60),
                onClick = {},
                leftIcon = Icons.Outlined.Home,
                text = "버튼 레이블",
                rightIcon = Icons.Filled.ArrowDropDown
            )
        }
    }
}
