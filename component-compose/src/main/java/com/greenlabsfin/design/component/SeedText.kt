package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.LocalSeedColorScheme
import com.greenlabsfin.design.core.LocalSeedTextStyle
import com.greenlabsfin.design.core.color.red60

@Composable
fun SeedText(
    modifier: Modifier = Modifier,
    text: String,
    leadingIcon: ImageVector? = null,
    leadingIconColor: Color = Color.Unspecified,
    trailingIcon: ImageVector? = null,
    trailingIconColor: Color = Color.Unspecified,
    iconAlignment: Alignment.Vertical = Alignment.CenterVertically,
    count: Int? = null,
    countColors: SeedCount.Colors? = null,
    badge: Boolean = false,
    color: Color = Color.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
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

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let { imageVector ->
            Icon(
                modifier = modifier
                    .weight(1f, false)
                    .align(iconAlignment),
                imageVector = imageVector,
                contentDescription = imageVector.name,
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
        trailingIcon?.let { imageVector ->
            Icon(
                modifier = modifier
                    .weight(1f, false)
                    .align(iconAlignment),
                imageVector = imageVector,
                contentDescription = imageVector.name,
                tint = trailingColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeedTextPreview() {
    SeedTheme {
        Surface(
            color = SeedTheme.colorScheme.container.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                SeedText(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = Icons.Filled.Menu,
                    text = "텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트텍스트",
                    badge = true,
                    count = 3,
                    style = SeedTheme.typoScheme.headline.largeBold,
                    countColors = SeedCountDefaults.Colors.neutral(),
                    trailingIcon = Icons.Filled.ArrowDropDown,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    iconAlignment = Alignment.Top,
                )

                SeedText(
                    modifier = Modifier.width(200.dp),
                    leadingIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    count = 3,
                    badge = true,
                    style = SeedTheme.typoScheme.headline.largeBold,
                    countColors = SeedCountDefaults.Colors.neutral(),
                    trailingIcon = Icons.Filled.ArrowDropDown
                )

                SeedText(
                    modifier = Modifier.wrapContentSize(),
                    leadingIcon = Icons.Filled.Menu,
                    text = "텍스트",
                    badge = true,
                    count = 3,
                    style = SeedTheme.typoScheme.body.smallMedium,
                    countColors = SeedCountDefaults.Colors.neutral(),
                    trailingIcon = Icons.Filled.ArrowDropDown
                )
            }
        }
    }
}
