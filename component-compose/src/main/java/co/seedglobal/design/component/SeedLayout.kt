package co.seedglobal.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ElevationOverlay
import androidx.compose.material.LocalAbsoluteElevation
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.LocalSeedBackgroundColor
import co.seedglobal.design.core.LocalSeedContentColor
import co.seedglobal.design.core.SeedTheme

@Composable
fun SeedTopBarLayout(
    modifier: Modifier = Modifier,
    title: String? = null,
    titleStyle: TextStyle = SeedTheme.typoScheme.headline.smallBold,
    titleColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    titleContent: @Composable (() -> Unit)? = null,
    titleAlignment: Alignment = Alignment.Center,
    trailingContent: @Composable (() -> Unit)? = null,
    navigationIcon: Painter? = null,
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
        ) {
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
    navigationIcon: Painter? = null,
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
fun SeedLoadingLayout(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize()) {
        content()
        if (isLoading) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                awaitPointerEvent(pass = PointerEventPass.Initial)
                                    .changes
                                    .forEach(PointerInputChange::consume)
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize(),
                    onDraw = { drawRect(color = Color.Transparent) })
                CircularProgressIndicator(
                    color = SeedTheme.colorScheme.contents.primary
                )
            }
        }
    }
}

@Composable
fun SeedSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = LocalSeedBackgroundColor.current,
    contentColor: Color = LocalSeedContentColor.current,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    val absoluteElevation = LocalAbsoluteElevation.current + elevation
    CompositionLocalProvider(
        LocalSeedContentColor provides contentColor,
        LocalAbsoluteElevation provides absoluteElevation
    ) {
        Box(
            modifier = modifier
                .surface(
                    shape = shape,
                    backgroundColor = surfaceColorAtElevation(
                        color = color,
                        elevationOverlay = LocalElevationOverlay.current,
                        absoluteElevation = absoluteElevation
                    ),
                    border = border,
                    elevation = elevation
                )
                .semantics(mergeDescendants = false) {}
                .pointerInput(Unit) {},
            propagateMinConstraints = true
        ) {
            content()
        }
    }
}

private fun Modifier.surface(
    shape: Shape,
    backgroundColor: Color,
    border: BorderStroke?,
    elevation: Dp,
) = this
    .shadow(elevation, shape, clip = false)
    .then(if (border != null) Modifier.border(border, shape) else Modifier)
    .background(color = backgroundColor, shape = shape)
    .clip(shape)

@Composable
private fun surfaceColorAtElevation(
    color: Color,
    elevationOverlay: ElevationOverlay?,
    absoluteElevation: Dp,
): Color {
    return if (color == SeedTheme.colorScheme.container.background && elevationOverlay != null) {
        elevationOverlay.apply(color, absoluteElevation)
    } else {
        color
    }
}
