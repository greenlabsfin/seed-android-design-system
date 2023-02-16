package co.seedglobal.design.component

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import co.seedglobal.design.core.LocalSeedContentColor
import co.seedglobal.design.core.SeedTheme

@Composable
fun SeedTab(
    selected: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selectedContentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
    unselectedContentColor: Color = SeedTheme.colorScheme.contents.neutralSecondary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    TabTransition(
        activeColor = selectedContentColor,
        inactiveColor = unselectedContentColor,
        selected = selected
    ) {
        Column(
            modifier = modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = null
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            content = content
        )
    }
}

@Composable
private fun TabTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable () -> Unit,
) {
    val transition = updateTransition(selected, label = "selectedTransition")
    val color by transition.animateColor(transitionSpec = {
        if (false isTransitioningTo true) {
            tween(
                durationMillis = TabFadeInAnimationDuration,
                delayMillis = TabFadeInAnimationDelay,
                easing = LinearEasing
            )
        } else {
            tween(
                durationMillis = TabFadeOutAnimationDuration,
                easing = LinearEasing
            )
        }
    }, label = "colorTransition") {
        if (it) activeColor else inactiveColor
    }

    CompositionLocalProvider(
        LocalSeedContentColor provides color,
        content = content
    )
}


// Tab transition specifications
private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100
