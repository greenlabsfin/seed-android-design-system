package co.seedglobal.design.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMaxBy
import co.seedglobal.design.core.SeedTheme

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
fun SeedBottomSheetScaffold(
    modifier: Modifier = Modifier,
    scrimColor: Color = SeedBottomSheetDefaults.scrimColor,
    sheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    isFixed: Boolean = false,
    sheetShape: Shape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
    ),
    sheetElevation: Dp = SeedBottomSheetDefaults.elevation,
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
