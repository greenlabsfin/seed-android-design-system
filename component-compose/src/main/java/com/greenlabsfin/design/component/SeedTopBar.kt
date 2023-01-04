package com.greenlabsfin.design.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.LocalSeedBackgroundColor

object SeedTopBarDefaults {
    val height = 56.dp
    val horizontalPadding = 4.dp

    fun paddingOf(
        start: Dp = 0.dp,
        end: Dp = 0.dp,
    ) = PaddingValues(
        start = maxOf(0.dp, start.minus(horizontalPadding)),
        end = maxOf(0.dp, end.minus(horizontalPadding)),
    )

    fun paddingOf(
        horizontal: Dp = 0.dp,
    ) = PaddingValues(
        horizontal = maxOf(0.dp, horizontal.minus(horizontalPadding)),
    )
}

@Composable
fun SeedTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = SeedTheme.typoScheme.headline.smallBold,
    titleColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    titleContent: @Composable (() -> Unit)? = null,
    titleAlignment: Alignment = Alignment.Center,
    trailingContent: @Composable (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    onNavigationClick: () -> Unit = {},
    topBarPadding: PaddingValues = PaddingValues(),
    color: Color = LocalSeedBackgroundColor.current,
    state: SeedBarState = rememberSeedBarState(),
    hideWhileScrollUp: Boolean = false,
    forceShowDivider: Boolean = false,
) {
    val heightPixel = with(LocalDensity.current) { SeedTopBarDefaults.height.toPx() }

    val yPosition by animateFloatAsState(
        if (state.visible.not() && hideWhileScrollUp) heightPixel.unaryMinus() else 0f,
        animationSpec = defaultBarVisibilityAnimationSpec(),
    )
    val animatedHeight by animateDpAsState(
        targetValue = if (state.visible) SeedTopBarDefaults.height else 0.dp,
        animationSpec = defaultBarVisibilityAnimationSpec()
    )

    Surface(
        modifier = Modifier.then(
            if (state.animated) Modifier.graphicsLayer { translationY = yPosition }
            else Modifier.offset(
                y = if (state.visible.not() && hideWhileScrollUp) SeedTopBarDefaults.height.unaryMinus()
                else 0.dp
            )
        ),
        color = color,
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .then(
                    when {
                        state.visible || hideWhileScrollUp.not() ->
                            Modifier.height(SeedTopBarDefaults.height)
                        state.animated -> Modifier.height(animatedHeight)
                        else -> Modifier.height(SeedTopBarDefaults.height)
                    }
                )
                .background(color)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.9f)
                    .padding(topBarPadding.merge(other = PaddingValues(horizontal = SeedTopBarDefaults.horizontalPadding))),
            ) {
                navigationIcon?.let {
                    SeedIcon(
                        modifier = Modifier
                            .size(48.dp)
                            .padding(12.dp)
                            .align(Alignment.CenterStart)
                            .clickable(
                                onClick = onNavigationClick,
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                            ),
                        imageVector = it,
                        contentDescription = "navigation"
                    )
                }
                if (titleContent != null || title != null) {
                    Box(modifier = Modifier.align(titleAlignment)) {
                        when {
                            titleContent != null -> {
                                titleContent()
                            }
                            title != null -> {
                                SeedText(
                                    text = title,
                                    style = titleStyle,
                                    color = titleColor,
                                )
                            }
                        }
                    }
                }

                trailingContent?.let {
                    Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                        trailingContent()
                    }
                }
            }

            if (forceShowDivider || (state.visible.not() && hideWhileScrollUp.not())) {
                Divider(color = SeedTheme.colorScheme.container.divider)
            }
        }
    }
}
