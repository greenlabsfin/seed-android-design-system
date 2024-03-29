package co.seedglobal.design.component

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.black
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

typealias SeedBottomSheetValue = ModalBottomSheetValue

@Composable
fun SeedBottomSheet(
    modifier: Modifier = Modifier,
    scrimColor: Color = SeedBottomSheetDefaults.scrimColor,
    sheetState: SeedBottomSheetState = rememberSeedBottomSheetState(initialValue = SeedBottomSheetValue.Hidden),
    sheetShape: Shape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp,
    ),
    sheetElevation: Dp = SeedBottomSheetDefaults.elevation,
    sheetBackgroundColor: Color = SeedTheme.colorScheme.container.background,
    sheetContentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    fixed: Boolean = true,
    onDismiss: () -> Unit = {},
    sheetContent: @Composable ColumnScope.() -> Unit,
) {
    if (fixed) {
        BoxWithConstraints(modifier) {
            val fullHeight = constraints.maxHeight.toFloat()
            val sheetHeightState = remember { mutableStateOf<Float?>(null) }
            Box(modifier = Modifier.fillMaxSize()) {
                Scrim(
                    color = scrimColor,
                    onDismiss = onDismiss,
                    visible = sheetState.targetValue != SeedBottomSheetValue.Hidden
                )
            }
            SeedSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset {
                        val y = sheetState.offset.value.roundToInt()
                        IntOffset(0, y)
                    }
                    .bottomSheetFixedSwipeable(sheetState, fullHeight, sheetHeightState)
                    .onGloballyPositioned {
                        sheetHeightState.value = it.size.height.toFloat()
                    },
                shape = sheetShape,
                elevation = sheetElevation,
                color = sheetBackgroundColor,
                contentColor = sheetContentColor
            ) {
                Column(content = sheetContent)
            }
        }
    } else {
        ModalBottomSheetLayout(
            sheetContent = sheetContent,
            modifier = modifier,
            sheetState = sheetState.state,
            sheetShape = sheetShape,
            sheetElevation = sheetElevation,
            sheetBackgroundColor = sheetBackgroundColor,
            sheetContentColor = sheetContentColor,
            scrimColor = scrimColor,
        ) {

        }
    }
}

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
    sheetElevation: Dp = SeedBottomSheetDefaults.elevation,
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
            sheetState = sheetState.state,
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
    val elevation = 16.dp

    val scrimColor: Color
        @Composable
        get() = black.copy(alpha = .4f)
}

@Composable
private fun SeedFixedBottomSheetLayout(
    modifier: Modifier = Modifier,
    scrimColor: Color = SeedBottomSheetDefaults.scrimColor,
    sheetState: SeedBottomSheetState = rememberSeedBottomSheetState(SeedBottomSheetValue.Hidden),
    sheetShape: Shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    sheetElevation: Dp = SeedBottomSheetDefaults.elevation,
    sheetBackgroundColor: Color = SeedTheme.colorScheme.container.background,
    sheetContentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    sheetContent: @Composable ColumnScope.() -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    BoxWithConstraints(modifier) {
        val fullHeight = constraints.maxHeight.toFloat()
        val sheetHeightState = remember { mutableStateOf<Float?>(null) }
        Box(modifier = Modifier.fillMaxSize()) {
            content()
            Scrim(
                color = scrimColor,
                onDismiss = { scope.launch { sheetState.hide() } },
                visible = sheetState.targetValue != SeedBottomSheetValue.Hidden
            )
        }
        SeedSurface(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    val y = sheetState.offset.value.roundToInt()
                    IntOffset(0, y)
                }
                .bottomSheetFixedSwipeable(sheetState, fullHeight, sheetHeightState)
                .onGloballyPositioned {
                    sheetHeightState.value = it.size.height.toFloat()
                }
                .semantics {
                    if (sheetState.isVisible) {
                        dismiss {
                            scope.launch { sheetState.hide() }
                            true
                        }
                    }
                },
            shape = sheetShape,
            elevation = sheetElevation,
            color = sheetBackgroundColor,
            contentColor = sheetContentColor,
        ) {
            Column(content = sheetContent)
        }
    }
}

private fun Modifier.bottomSheetFixedSwipeable(
    sheetState: SeedBottomSheetState,
    fullHeight: Float,
    sheetHeightState: State<Float?>,
): Modifier {
    val sheetHeight = sheetHeightState.value
    val modifier = if (sheetHeight != null && sheetHeight > 0f) {
        val anchors = mapOf(
            fullHeight to SeedBottomSheetValue.Hidden,
            fullHeight - sheetHeight to SeedBottomSheetValue.Expanded
        )
        Modifier.swipeable(
            state = sheetState.state,
            anchors = anchors,
            orientation = Orientation.Vertical,
            enabled = false,
            resistance = null,
        )
    } else {
        Modifier
    }

    return this.then(modifier)
}


@Composable
fun Scrim(
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

class SeedBottomSheetState(
    initialValue: SeedBottomSheetValue,
    val keyboardController: SoftwareKeyboardController?,
    confirmStateChange: (SeedBottomSheetValue) -> Boolean = { true },
) {
    val state = ModalBottomSheetState(
        initialValue = initialValue,
        confirmStateChange = confirmStateChange
    )

    val isVisible: Boolean
        get() = state.isVisible

    val offset: State<Float>
        get() = state.offset

    val targetValue: SeedBottomSheetValue
        get() = state.targetValue

    suspend fun show(hideKeyboard: Boolean = true) {
        if (hideKeyboard) {
            keyboardController?.hide()
        }
        state.show()
    }

    suspend fun hide() {
        state.hide()
    }

    companion object {
        fun Saver(
            keyboardController: SoftwareKeyboardController?,
            confirmStateChange: (SeedBottomSheetValue) -> Boolean,
        ): Saver<SeedBottomSheetState, *> = Saver(
            save = { it.state.currentValue },
            restore = {
                SeedBottomSheetState(
                    initialValue = it,
                    keyboardController = keyboardController,
                    confirmStateChange = confirmStateChange,
                )
            }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun rememberSeedBottomSheetState(
    initialValue: SeedBottomSheetValue,
    confirmStateChange: (ModalBottomSheetValue) -> Boolean = { true },
): SeedBottomSheetState {
    val keyboardController = LocalSoftwareKeyboardController.current
    return rememberSaveable(
        initialValue, keyboardController, confirmStateChange,
        saver = SeedBottomSheetState.Saver(
            keyboardController = keyboardController,
            confirmStateChange = confirmStateChange
        )
    ) {
        SeedBottomSheetState(
            initialValue = initialValue,
            keyboardController = keyboardController,
            confirmStateChange = confirmStateChange
        )
    }
}
