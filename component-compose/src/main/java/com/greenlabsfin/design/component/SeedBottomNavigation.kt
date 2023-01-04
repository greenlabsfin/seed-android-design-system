package com.greenlabsfin.design.component

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.LocalSeedBackgroundColor
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.red60

object SeedBottomNavigationDefaults {
    val elevation = 12.dp
    val height = 80.dp
    val contentPadding = PaddingValues(top = 12.dp)
    val arrangement = Arrangement.spacedBy(3.dp)
}

object SeedBottomNavigation {
    enum class IconSize(val size: Dp) {
        Large(24.dp),
        Small(20.dp),
        ;

        val countSize: SeedCount.Size
            get() = when (this) {
                Large -> SeedCount.Size.Large
                Small -> SeedCount.Size.Medium
            }

        val badgeRadius: Dp
            get() = when (this) {
                Large -> 3.dp
                Small -> 2.dp
            }
    }
}

@Composable
fun SeedBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = LocalSeedBackgroundColor.current,
    elevation: Dp = SeedBottomNavigationDefaults.elevation,
    state: SeedBarState = rememberSeedBarState(false),
    hideWhileScrollUp: Boolean = false,
    content: @Composable RowScope.() -> Unit,
) {

    val heightPixel = with(LocalDensity.current) { SeedBottomNavigationDefaults.height.toPx() }
    val yPosition by animateFloatAsState(
        targetValue = when {
            state.visible.not() && hideWhileScrollUp -> heightPixel
            else -> 0f
        },
        animationSpec = defaultBarVisibilityAnimationSpec(),
    )
    val animatedHeight by animateDpAsState(
        targetValue =
        if (state.visible) SeedBottomNavigationDefaults.height
        else 0.dp,
        animationSpec = defaultBarVisibilityAnimationSpec()
    )

    Surface(
        color = backgroundColor,
        elevation = elevation,
        modifier = modifier.then(
            if (state.animated) Modifier.graphicsLayer { translationY = yPosition }
            else Modifier.offset(
                y = when {
                    state.visible.not() && hideWhileScrollUp -> SeedBottomNavigationDefaults.height
                    else -> 0.dp
                }
            )
        ),
        shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    when {
                        state.visible || hideWhileScrollUp.not() ->
                            Modifier.height(SeedBottomNavigationDefaults.height)
                        state.animated -> Modifier.height(animatedHeight)
                        else -> Modifier.height(SeedBottomNavigationDefaults.height)
                    }
                )
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content,
        )
    }
}

@Composable
fun RowScope.SeedBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    selectedIcon: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    unselectedIcon: Painter = selectedIcon,
    selectedContentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    unselectedContentColor: Color = SeedTheme.colorScheme.contents.neutralSecondary,
    enabled: Boolean = true,
    count: Int? = null,
    countColor: SeedCountColors = SeedCountDefaults.errorSecondaryColors(),
    badge: Boolean = false,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    iconSize: SeedBottomNavigation.IconSize = SeedBottomNavigation.IconSize.Small,
    contentPadding: PaddingValues = SeedBottomNavigationDefaults.contentPadding,
    verticalArrangement: Arrangement.Vertical = SeedBottomNavigationDefaults.arrangement,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    Box(
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null,
            )
            .weight(1f)
            .align(Alignment.CenterVertically),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = verticalArrangement,
        ) {
            Box(
                modifier = Modifier.size(iconSize.size)
            ) {
                SeedBottomNavigationIcon(
                    iconSize = iconSize,
                    selected = selected,
                    selectedIcon = selectedIcon,
                    unselectedIcon = unselectedIcon,
                    contentDescription = contentDescription,
                    selectedContentColor = selectedContentColor,
                    unselectedContentColor = unselectedContentColor,
                    badge = badge,
                    count = count,
                    countColor = countColor,
                )
            }
            if (alwaysShowLabel || selected) {
                label?.let {
                    Crossfade(targetState = selected) { selected ->
                        Decoration(
                            contentColor = if (selected) selectedContentColor else unselectedContentColor,
                            typography = SeedTheme.typoScheme.caption.xSmallRegular.merge(TextStyle(
                                color = if (selected) selectedContentColor else unselectedContentColor))
                        ) {
                            Box(
                                modifier = Modifier.height(18.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                label()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BoxScope.SeedBottomNavigationIcon(
    iconSize: SeedBottomNavigation.IconSize,
    selected: Boolean,
    selectedIcon: Painter,
    unselectedIcon: Painter = selectedIcon,
    contentDescription: String?,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    badge: Boolean,
    count: Int?,
    countColor: SeedCountColors,
) {
    Crossfade(targetState = selected) {
        SeedIcon(
            modifier = Modifier
                .size(iconSize.size)
                .align(Alignment.Center),
            painter = if (it) selectedIcon else unselectedIcon,
            contentDescription = contentDescription,
            tint = if (it) selectedContentColor else unselectedContentColor,
        )
    }
    if (badge) {
        Canvas(modifier = Modifier.offset(
            x = iconSize.size,
            y = 0.dp
        ), onDraw = {
            drawCircle(
                color = red60,
                radius = iconSize.badgeRadius.toPx()
            )
        })
    }
    count?.let {
        SeedCount(
            modifier = Modifier
                .offset(
                    x = 11.dp,
                    y = 5.dp.unaryMinus()
                ),
            size = iconSize.countSize,
            count = count,
            colors = countColor
        )
    }
}
