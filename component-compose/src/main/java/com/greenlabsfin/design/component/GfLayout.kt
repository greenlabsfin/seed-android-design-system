package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme

@Composable
fun GfScrollableTopBarLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(0.dp),
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
    hideWhileScrollUp: Boolean = false,
    content: LazyListScope.() -> Unit,
) {
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = contentPadding.merge(other = PaddingValues(top = GfTopBarDefaults.height)),
            state = listState,
            content = content,
            verticalArrangement = verticalArrangement
        )
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
            listState = listState,
            hideWhileScrollUp = hideWhileScrollUp,
        )
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
    color: Color = GfTheme.colorScheme.container.background,
    showDivider: Boolean = false,
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
        content()
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
    color: Color = GfTheme.colorScheme.container.background,
    showDivider: Boolean = false,
    sheetState: GfBottomSheetState = rememberGfBottomSheetState(initialValue = GfBottomSheetValue.Hidden),
    isFixed: Boolean = true,
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
