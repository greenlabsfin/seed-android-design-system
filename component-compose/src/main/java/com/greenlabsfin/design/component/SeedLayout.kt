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
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.LocalSeedBackgroundColor

@Composable
fun SeedBottomSheetScaffold(
    modifier: Modifier = Modifier,
    scrimColor: Color = SeedBottomSheetDefaults.scrimColor,
    sheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    isFixed: Boolean = false,
    sheetShape: Shape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
    ),
    sheetElevation: Dp = SeedBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = SeedTheme.colorScheme.container.background,
    sheetContentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    sheetContent: @Composable ColumnScope.() -> Unit,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    SeedBottomSheetLayout(
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
        SeedScaffold(
            modifier = modifier,
            topBar = topBar,
            bottomBar = bottomBar,
            content = content
        )
    }
}

@Composable
fun SeedScaffold(
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
fun SeedTopBarLayout(
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
    showDivider: Boolean = false,
    bottomBar: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        SeedTopBar(
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
fun SeedBottomSheetTopBarLayout(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = SeedTheme.typoScheme.headline.smallBold,
    titleColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    titleContent: @Composable (() -> Unit)? = null,
    titleAlignment: Alignment = Alignment.Center,
    trailingContent: @Composable (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    topBarPadding: PaddingValues = PaddingValues(),
    onNavigationClick: () -> Unit = {},
    color: Color = LocalSeedBackgroundColor.current,
    showDivider: Boolean = false,
    sheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    isFixed: Boolean = true,
    bottomBar: @Composable () -> Unit = {},
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    SeedBottomSheetLayout(
        sheetContent = sheetContent,
        isFixed = isFixed,
        sheetState = sheetState
    ) {
        SeedTopBarLayout(
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
