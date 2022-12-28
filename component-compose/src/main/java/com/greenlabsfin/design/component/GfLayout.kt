package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMaxBy
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.LocalGfBackgroundColor

@Composable
fun GfBottomSheetScaffold(
    modifier: Modifier = Modifier,
    scrimColor: Color = GfBottomSheetDefaults.scrimColor,
    sheetState: GfBottomSheetState = rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden),
    isFixed: Boolean = false,
    sheetShape: Shape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
    ),
    sheetElevation: Dp = GfBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = GfTheme.colorScheme.container.background,
    sheetContentColor: Color = GfTheme.colorScheme.contents.neutralPrimary,
    sheetContent: @Composable ColumnScope.() -> Unit,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    GfBottomSheetLayout(
        modifier = modifier,
        scrimColor = scrimColor,
        sheetState = sheetState,
        isFixed = isFixed,
        sheetShape = sheetShape,
        sheetElevation = sheetElevation,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        sheetContent = sheetContent
    ) {
        GfScaffold(
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            content = content
        )
    }
}

@Composable
fun GfScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Surface(modifier = modifier) {
        SubcomposeLayout { constraints ->
            val layoutWidth = constraints.maxWidth
            val layoutHeight = constraints.maxHeight
            val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

            layout(layoutWidth, layoutHeight) {
                val topBarPlaceable = subcompose("topBar", topBar).fastMap {
                    it.measure(looseConstraints)
                }
                val topBarHeight = topBarPlaceable.fastMaxBy { it.height }?.height ?: 0

                val bottomBarPlaceable = subcompose("bottomBar", bottomBar)
                    .fastMap { it.measure(looseConstraints) }
                val bottomBarHeight = bottomBarPlaceable.fastMaxBy { it.height }?.height ?: 0

                val bodyContentHeight = layoutHeight - topBarHeight - bottomBarHeight
                val bodyContentPlaceables = subcompose("body", content)
                    .fastMap { it.measure(looseConstraints.copy(maxHeight = bodyContentHeight)) }

                bodyContentPlaceables.fastForEach {
                    it.place(0, topBarHeight)
                }
                topBarPlaceable.fastForEach {
                    it.place(0, 0)
                }
                bottomBarPlaceable.fastForEach {
                    it.place(0, layoutHeight - bottomBarHeight)
                }
            }
        }
    }
}

@Composable
fun GfTopBarLayout(
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
    color: Color = LocalGfBackgroundColor.current,
    showDivider: Boolean = false,
    bottomBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        GfTopBar(
            modifier = modifier,
            title = title,
            titleStyle = titleStyle,
            titleColor = titleColor,
            titleContent = titleContent,
            titleAlignment = titleAlignment,
            trailingContent = trailingContent,
            navigationIcon = navigationIcon,
            onNavigationClick = onNavigationClick,
            topBarPadding = topBarPadding,
            color = color,
            forceShowDivider = showDivider,
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .weight(1f, true)) {
            content()
        }
        bottomBar?.let {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                bottomBar()
            }
        }
    }
}

@Composable
fun GfBottomSheetTopBarLayout(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = GfTheme.typoScheme.headline.smallBold,
    titleColor: Color = GfTheme.colorScheme.contents.neutralPrimary,
    titleContent: @Composable (() -> Unit)? = null,
    titleAlignment: Alignment = Alignment.Center,
    trailingContent: @Composable (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    topBarPadding: PaddingValues = PaddingValues(),
    onNavigationClick: () -> Unit = {},
    color: Color = LocalGfBackgroundColor.current,
    showDivider: Boolean = false,
    sheetState: GfBottomSheetState = rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden),
    isFixed: Boolean = true,
    bottomBar: @Composable () -> Unit = {},
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    GfBottomSheetLayout(
        sheetContent = sheetContent,
        isFixed = isFixed,
        sheetState = sheetState
    ) {
        GfTopBarLayout(
            modifier = modifier,
            title = title,
            titleStyle = titleStyle,
            titleColor = titleColor,
            titleContent = titleContent,
            titleAlignment = titleAlignment,
            trailingContent = trailingContent,
            navigationIcon = navigationIcon,
            onNavigationClick = onNavigationClick,
            topBarPadding = topBarPadding,
            color = color,
            showDivider = showDivider,
            bottomBar = bottomBar,
            content = content
        )
    }
}

@Composable
fun PaddingValues.merge(other: PaddingValues): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current
    return PaddingValues(
        start = calculateStartPadding(layoutDirection).plus(other.calculateStartPadding(
            layoutDirection)),
        top = calculateTopPadding().plus(other.calculateTopPadding()),
        bottom = calculateBottomPadding().plus(other.calculateBottomPadding()),
        end = calculateEndPadding(layoutDirection).plus(other.calculateEndPadding(layoutDirection))
    )
}
