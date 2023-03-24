package co.seedglobal.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.LocalSeedColorScheme
import co.seedglobal.design.core.LocalSeedTextStyle
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.red60

@Composable
fun SeedText(
    modifier: Modifier = Modifier,
    text: String,
    leadingIcon: Painter? = null,
    leadingIconContentDescription: String? = null,
    leadingIconColor: Color = Color.Unspecified,
    leadingIconSize: Dp = 16.dp,
    trailingIcon: Painter? = null,
    trailingIconContentDescription: String? = null,
    trailingIconSize: Dp = 16.dp,
    trailingIconColor: Color = Color.Unspecified,
    iconAlignment: Alignment.Vertical = Alignment.CenterVertically,
    count: Int? = null,
    countColors: SeedCountColors? = null,
    badge: Boolean = false,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalSeedTextStyle.current,
    enabled: Boolean = true,
    fillText: Boolean = false,
) {
    // text color priority color -> style -> local provider
    val takenTextColor = color.takeOrElse {
        style.color.takeOrElse {
            LocalSeedColorScheme.current.contents.neutralPrimary
        }
    }

    val takenLeadingIconColor = leadingIconColor.takeOrElse { takenTextColor }
    val takenTrailingIconColor = trailingIconColor.takeOrElse { takenTextColor }

    val textColor
            by rememberUpdatedState(
                if (enabled) takenTextColor
                else takenTextColor.copy(alpha = .3f)
            )
    val leadingColor
            by rememberUpdatedState(
                if (enabled) takenLeadingIconColor
                else takenLeadingIconColor.copy(alpha = .3f)
            )
    val trailingColor
            by rememberUpdatedState(
                if (enabled) takenTrailingIconColor
                else takenTrailingIconColor.copy(alpha = .3f)
            )

    data class IconData(
        val painter: Painter,
        val description: String?,
        val size: Dp,
    )

    val leadingContent: IconData?
    val trailingContent: IconData?
    when (LocalLayoutDirection.current) {
        LayoutDirection.Ltr -> {
            leadingContent = leadingIcon?.let {
                IconData(
                    painter = it,
                    description = leadingIconContentDescription,
                    size = leadingIconSize
                )
            }
            trailingContent = trailingIcon?.let {
                IconData(
                    painter = it,
                    description = trailingIconContentDescription,
                    size = trailingIconSize
                )
            }
        }
        LayoutDirection.Rtl -> {
            leadingContent = trailingIcon?.let {
                IconData(
                    painter = it,
                    description = trailingIconContentDescription,
                    size = trailingIconSize
                )
            }
            trailingContent = leadingIcon?.let {
                IconData(
                    painter = it,
                    description = leadingIconContentDescription,
                    size = leadingIconSize
                )
            }
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingContent?.let { iconData ->
            SeedIcon(
                modifier = Modifier
                    .size(iconData.size)
                    .weight(1f, false)
                    .align(iconAlignment),
                painter = iconData.painter,
                contentDescription = iconData.description,
                tint = leadingColor
            )
        }
        Row(
            modifier = Modifier.weight(8f, fillText),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            BadgedBox(
                modifier = if (badge) Modifier.padding(end = 4.dp) else Modifier,
                badge = {
                    if (badge) {
                        Badge(
                            modifier = Modifier
                                .size(4.dp)
                                .offset(4.dp, 4.dp),
                            backgroundColor = red60

                        )
                    }
                },
                content = {
                    Text(
                        text = text,
                        color = textColor,
                        style = style,
                        onTextLayout = onTextLayout,
                        textAlign = textAlign,
                        overflow = overflow,
                        softWrap = softWrap,
                        maxLines = maxLines,
                    )
                }
            )
            count?.let {
                countColors?.let {
                    SeedCount(
                        count = count,
                        colors = countColors,
                        enabled = enabled,
                    )
                }
            }
        }
        trailingContent?.let { iconData ->
            SeedIcon(
                modifier = Modifier
                    .size(iconData.size)
                    .weight(1f, false)
                    .align(iconAlignment),
                painter = iconData.painter,
                contentDescription = iconData.description,
                tint = trailingColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeedTextPreview() {
    SeedTheme {
        CompositionLocalProvider(
            LocalLayoutDirection provides LayoutDirection.Ltr
        ) {
            SeedSurface(
                color = SeedTheme.colorScheme.container.background
            ) {
                SeedText(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = Icons.Filled.Menu.toPainter(),
                    text = "텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트",
                    badge = true,
                    count = 3,
                    style = SeedTheme.typoScheme.headline.largeBold,
                    countColors = SeedCountDefaults.neutralColors(),
                    trailingIcon = Icons.Filled.ArrowDropDown.toPainter(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    iconAlignment = Alignment.Top,
                )

                SeedText(
                    modifier = Modifier.width(200.dp),
                    leadingIcon = Icons.Filled.Menu.toPainter(),
                    text = "텍스트",
                    count = 3,
                    badge = true,
                    style = SeedTheme.typoScheme.headline.largeBold,
                    countColors = SeedCountDefaults.neutralColors(),
                    trailingIcon = Icons.Filled.ArrowDropDown.toPainter()
                )

                SeedText(
                    modifier = Modifier.wrapContentSize(),
                    leadingIcon = Icons.Filled.Menu.toPainter(),
                    text = "텍스트",
                    badge = true,
                    count = 3,
                    style = SeedTheme.typoScheme.body.smallMedium,
                    countColors = SeedCountDefaults.neutralColors(),
                    trailingIcon = Icons.Filled.ArrowDropDown.toPainter()
                )
            }
        }
    }
}
