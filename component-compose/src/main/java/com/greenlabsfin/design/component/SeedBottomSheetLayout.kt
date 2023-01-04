package com.greenlabsfin.design.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.SwipeableDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.black

typealias SeedBottomSheetState = ModalBottomSheetState
typealias SeedBottomSheetValue = ModalBottomSheetValue

@Composable
fun SeedBottomSheetLayout(
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
    content: @Composable () -> Unit,
) {
    if (isFixed) {
        SeedFixedBottomSheetLayout(
            modifier = modifier,
            scrimColor = scrimColor,
            sheetState = sheetState,
            sheetShape = sheetShape,
            sheetElevation = sheetElevation,
            sheetBackgroundColor = sheetBackgroundColor,
            sheetContentColor = sheetContentColor,
            sheetContent = sheetContent,
            content = content
        )
    } else {
        ModalBottomSheetLayout(
            modifier = modifier,
            scrimColor = scrimColor,
            sheetState = sheetState,
            sheetShape = sheetShape,
            sheetElevation = sheetElevation,
            sheetBackgroundColor = sheetBackgroundColor,
            sheetContentColor = sheetContentColor,
            sheetContent = sheetContent,
            content = content
        )
    }
}

object SeedBottomSheetDefaults {
    val Elevation = 16.dp

    val scrimColor: Color
        @Composable
        get() = black.copy(alpha = .4f)
}

@Composable
fun rememberSeedBottomSheetState(
    initialValue: SeedBottomSheetValue,
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    confirmStateChange: (SeedBottomSheetValue) -> Boolean = { true },
): SeedBottomSheetState {
    return rememberSaveable(
        animationSpec,
        saver = SeedBottomSheetState.Saver(
            animationSpec = animationSpec,
            skipHalfExpanded = true,
            confirmStateChange = confirmStateChange
        )
    ) {
        SeedBottomSheetState(
            initialValue = initialValue,
            animationSpec = animationSpec,
            confirmStateChange = confirmStateChange
        )
    }
}


@Composable
internal fun Scrim(
    color: Color,
    onDismiss: () -> Unit,
    visible: Boolean,
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec(),
        )
        val closeSheet = "closeSheet"
        val dismissModifier = if (visible) {
            Modifier
                .pointerInput(onDismiss) { detectTapGestures { onDismiss() } }
                .semantics(mergeDescendants = true) {
                    contentDescription = closeSheet
                    onClick { onDismiss();true }
                }
        } else {
            Modifier
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .then(dismissModifier),
            onDraw = {
                drawRect(color = color, alpha = alpha)
            }
        )
    }
}

