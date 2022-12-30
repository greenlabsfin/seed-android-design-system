package com.greenlabsfin.design.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class GfBarState(
    initialVisibility: Boolean,
) {
    var visible: Boolean by mutableStateOf(initialVisibility)
        private set

    var animated: Boolean by mutableStateOf(false)

    fun hide(animated: Boolean = false) {
        this.animated = animated
        visible = false
    }

    fun show(animated: Boolean = false) {
        this.animated = animated
        visible = true
    }

    companion object {
        val Saver: Saver<GfBarState, *> = Saver(
            save = { it.visible },
            restore = { GfBarState(it) }
        )
    }
}

@Composable
fun rememberGfBarState(initialVisibility: Boolean = true): GfBarState {
    return rememberSaveable(saver = GfBarState.Saver) {
        GfBarState(initialVisibility)
    }
}

fun <T> defaultBarVisibilityAnimationSpec() = spring<T>(stiffness = Spring.StiffnessMediumLow)
