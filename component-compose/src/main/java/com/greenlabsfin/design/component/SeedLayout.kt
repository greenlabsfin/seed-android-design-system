package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import com.greenlabsfin.design.core.LocalSeedBackgroundColor
import com.greenlabsfin.design.core.SeedTheme

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
