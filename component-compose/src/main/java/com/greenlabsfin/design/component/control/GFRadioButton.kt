package com.greenlabsfin.design.component.control

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray40

@Composable
fun GFRadioButton(
    selected: Boolean,
    enabled: Boolean = true,
    text: String? = null,
    textStyle: TextStyle = GfTheme.typoScheme.body.mediumRegular,
    colors: ControlColors = GFControl.Colors.radioPrimary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {

    val backgroundAnimation =
        animateColorAsState(
            targetValue = colors.controlColor(
                enabled = enabled,
                selected = selected).value
        )
    val dotAnimation =
        animateColorAsState(
            targetValue = colors.controlDotColor(
                enabled = enabled,
                selected = selected).value
        )

    val paddingModifier = text?.let {
        Modifier.padding(end = 8.dp)
    } ?: Modifier

    val selectedBorderModifier =
        if (selected.not()) {
            Modifier.border(width = 1.dp,
                color = gray40,
                shape = CircleShape)
        } else Modifier

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .then(
                    paddingModifier
                )
                .size(radioBackgroundSize)
                .selectable(
                    selected = selected,
                    enabled = enabled,
                    role = Role.RadioButton,
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        bounded = false,
                        radius = 24.dp
                    ),
                    onClick = onClick
                )
                .background(
                    color = backgroundAnimation.value,
                    shape = CircleShape
                )
                .then(
                    selectedBorderModifier
                ),
            contentAlignment = Alignment.Center,
        ) {
            Spacer(
                modifier = Modifier
                    .size(radioDotSize)
                    .background(
                        color = dotAnimation.value,
                        shape = CircleShape
                    )
            )
        }

        text?.let {
            GfText(
                text = it,
                style = textStyle
            )
        }
    }
}

private val radioBackgroundSize = 24.dp
private val radioDotSize = 8.dp