package co.seedglobal.design.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.RecomposeScope
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.AccessibilityManager
import androidx.compose.ui.platform.LocalAccessibilityManager
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.resume

@Composable
fun rememberSeedSnackbarState() = remember { SeedSnackbarHostState() }

@Stable
class SeedSnackbarHostState {
    private val mutex = Mutex()

    var currentSnackbarData by mutableStateOf<SeedSnackbarData?>(null)
        private set

    suspend fun showSnackbar(
        message: String,
        icon: SeedIconResource? = null,
        iconColor: Color? = null,
        actionLabel: String? = null,
        duration: SeedSnackbarDuration = SeedSnackbarDuration.Short,
    ): SeedSnackbarResult = mutex.withLock {
        try {
            return suspendCancellableCoroutine { continuation ->
                currentSnackbarData = SeedSnackbarDataImpl(
                    message = message,
                    actionLabel = actionLabel,
                    icon = icon,
                    iconColor = iconColor,
                    duration = duration,
                    continuation = continuation
                )
            }
        } finally {
            currentSnackbarData = null
        }
    }

    @Stable
    private class SeedSnackbarDataImpl(
        override val message: String,
        override val actionLabel: String?,
        override val icon: SeedIconResource?,
        override val iconColor: Color?,
        override val duration: SeedSnackbarDuration,
        private val continuation: CancellableContinuation<SeedSnackbarResult>,
    ) : SeedSnackbarData {
        override fun performAction() {
            if (continuation.isActive) continuation.resume(SeedSnackbarResult.ActionPerformed)
        }

        override fun dismiss() {
            if (continuation.isActive) continuation.resume(SeedSnackbarResult.Dismissed)
        }
    }
}

@Composable
fun SeedSnackbarHost(
    hostState: SeedSnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SeedSnackbarData) -> Unit = { SeedSnackbar(it) },
) {
    val currentSnackbarData = hostState.currentSnackbarData
    val accessibilityManager = LocalAccessibilityManager.current
    LaunchedEffect(currentSnackbarData) {
        if (currentSnackbarData != null) {
            val duration = currentSnackbarData.duration.toMillis(
                currentSnackbarData.actionLabel != null,
                accessibilityManager
            )
            delay(duration)
            currentSnackbarData.dismiss()
        }
    }
    FadeInFadeOutWithScale(
        current = hostState.currentSnackbarData,
        modifier = modifier,
        content = snackbar,
    )
}

interface SeedSnackbarData {
    val message: String
    val actionLabel: String?
    val icon: SeedIconResource?
    val iconColor: Color?
    val duration: SeedSnackbarDuration

    fun performAction()

    fun dismiss()
}

enum class SeedSnackbarResult {
    Dismissed,
    ActionPerformed,
}

enum class SeedSnackbarDuration {
    Short,
    Long,
    Indefinite
}

internal fun SeedSnackbarDuration.toMillis(
    hasAction: Boolean,
    accessibilityManager: AccessibilityManager?,
): Long {
    val original = when (this) {
        SeedSnackbarDuration.Indefinite -> Long.MAX_VALUE
        SeedSnackbarDuration.Long -> 5000L
        SeedSnackbarDuration.Short -> 3000L
    }
    if (accessibilityManager == null) {
        return original
    }
    return accessibilityManager.calculateRecommendedTimeoutMillis(
        original,
        containsIcons = true,
        containsText = true,
        containsControls = hasAction
    )
}

@Composable
private fun FadeInFadeOutWithScale(
    current: SeedSnackbarData?,
    modifier: Modifier = Modifier,
    content: @Composable (SeedSnackbarData) -> Unit,
) {
    val state = remember { FadeInFadeOutState<SeedSnackbarData?>() }
    if (current != state.current) {
        state.current = current
        val keys = state.items.map { it.key }.toMutableList()
        if (!keys.contains(current)) {
            keys.add(current)
        }
        state.items.clear()
        keys.filterNotNull().mapTo(state.items) { key ->
            FadeInFadeOutAnimationItem(key) { children ->
                val isVisible = key == current
                val duration = if (isVisible) SnackbarFadeInMillis else SnackbarFadeOutMillis
                val delay = SnackbarFadeOutMillis + SnackbarInBetweenDelayMillis
                val animationDelay = if (isVisible && keys.filterNotNull().size != 1) delay else 0
                val opacity = animatedOpacity(
                    animation = tween(
                        easing = LinearEasing,
                        delayMillis = animationDelay,
                        durationMillis = duration
                    ),
                    visible = isVisible,
                    onAnimationFinish = {
                        if (key != state.current) {
                            // leave only the current in the list
                            state.items.removeAll { it.key == key }
                            state.scope?.invalidate()
                        }
                    }
                )
                val scale = animatedScale(
                    animation = tween(
                        easing = FastOutSlowInEasing,
                        delayMillis = animationDelay,
                        durationMillis = duration
                    ),
                    visible = isVisible
                )
                Box(
                    Modifier
                        .graphicsLayer(
                            scaleX = scale.value,
                            scaleY = scale.value,
                            alpha = opacity.value
                        )
                        .semantics {
                            liveRegion = LiveRegionMode.Polite
                            dismiss { key.dismiss(); true }
                        }
                ) {
                    children()
                }
            }
        }
    }
    Box(modifier) {
        state.scope = currentRecomposeScope
        state.items.fastForEach { (item, opacity) ->
            key(item) {
                opacity {
                    content(item!!)
                }
            }
        }
    }
}

private class FadeInFadeOutState<T> {
    // we use Any here as something which will not be equals to the real initial value
    var current: Any? = Any()
    var items = mutableListOf<FadeInFadeOutAnimationItem<T>>()
    var scope: RecomposeScope? = null
}

private data class FadeInFadeOutAnimationItem<T>(
    val key: T,
    val transition: FadeInFadeOutTransition,
)

private typealias FadeInFadeOutTransition = @Composable (content: @Composable () -> Unit) -> Unit

@Composable
private fun animatedOpacity(
    animation: AnimationSpec<Float>,
    visible: Boolean,
    onAnimationFinish: () -> Unit = {},
): State<Float> {
    val alpha = remember { Animatable(if (!visible) 1f else 0f) }
    LaunchedEffect(visible) {
        alpha.animateTo(
            if (visible) 1f else 0f,
            animationSpec = animation
        )
        onAnimationFinish()
    }
    return alpha.asState()
}

@Composable
private fun animatedScale(animation: AnimationSpec<Float>, visible: Boolean): State<Float> {
    val scale = remember { Animatable(if (!visible) 1f else 0.8f) }
    LaunchedEffect(visible) {
        scale.animateTo(
            if (visible) 1f else 0.8f,
            animationSpec = animation
        )
    }
    return scale.asState()
}

private const val SnackbarFadeInMillis = 150
private const val SnackbarFadeOutMillis = 75
private const val SnackbarInBetweenDelayMillis = 0
