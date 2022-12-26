package com.greenlabsfin.design.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme

object GfTopBarDefaults {
    val height = 56.dp
    val horizontalPadding = 4.dp
    val scrollThreshold = 30

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
fun GfTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = GfTheme.typoScheme.headline.smallBold,
    titleColor: Color = GfTheme.colorScheme.contents.neutralPrimary,
    titleContent: @Composable (() -> Unit)? = null,
    titleAlignment: Alignment = Alignment.Center,
    trailingContent: @Composable (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    onNavigationClick: () -> Unit = {},
    topBarPadding: PaddingValues = PaddingValues(),
    color: Color = GfTheme.colorScheme.container.background,
    listState: LazyListState = rememberLazyListState(),
    hideWhileScrollUp: Boolean = false,
    forceShowDivider: Boolean = false,
) {

    var isUpScrolling by remember { mutableStateOf(false) }
    var firstVisibleItemIndex by remember { mutableStateOf(0) }
    var scrollOffset by remember { mutableStateOf(0) }



    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
            .collect {
                val index = it.first
                val offset = it.second
                if (index != firstVisibleItemIndex) {
                    scrollOffset = offset
                }
                firstVisibleItemIndex = index
                val offsetDelta = offset.minus(scrollOffset)
                if (offsetDelta == 0) return@collect
                val isDown = offsetDelta < GfTopBarDefaults.scrollThreshold.unaryMinus()
                val isUp = offsetDelta > GfTopBarDefaults.scrollThreshold
                isUpScrolling = if (isUpScrolling) {
                    isDown.not()
                } else {
                    isUp
                }
            }
    }
    val topBarHeightPixel = with(LocalDensity.current) { GfTopBarDefaults.height.toPx() }
    val yPosition by animateFloatAsState(
        if (isUpScrolling && hideWhileScrollUp) topBarHeightPixel.unaryMinus()
        else 0f
    )

    Surface(
        modifier = Modifier.graphicsLayer { translationY = yPosition },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(GfTopBarDefaults.height)
                .background(color)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.9f)
                    .padding(topBarPadding.merge(other = PaddingValues(horizontal = GfTopBarDefaults.horizontalPadding))),
            ) {
                navigationIcon?.let {
                    GfIcon(
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
                                GfText(
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

            if (forceShowDivider || (isUpScrolling && hideWhileScrollUp.not())) {
                Divider(color = GfTheme.colorScheme.container.divider)
            }
        }
    }
}
