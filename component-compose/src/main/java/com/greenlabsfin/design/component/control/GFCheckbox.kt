package com.greenlabsfin.design.component.control

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.component.GfIcon
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.icons.Icons
import com.greenlabsfin.design.component.icons.bold.CheckLine
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray40

@Composable
fun GFCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    text: String? = null,
    textStyle: TextStyle = GfTheme.typoScheme.body.mediumRegular,
    colors: ControlColors = GFControl.Colors.checkboxPrimary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: (Boolean) -> Unit,
) {

    val backgroundAnimation =
        animateColorAsState(
            targetValue = colors.controlColor(
                enabled = enabled,
                selected = checked
            ).value
        )
    val iconAnimation =
        animateColorAsState(
            targetValue = colors.controlDotColor(
                enabled = enabled,
                selected = checked).value
        )

    val paddingModifier = text?.let {
        Modifier.padding(end = 8.dp)
    } ?: Modifier

    val checkedBorderModifier =
        if (checked.not()) {
            Modifier.border(width = 1.dp,
                color = gray40,
                shape = RoundedCornerShape(checkBoxRadius))
        } else {
            Modifier
        }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = modifier
                .then(
                    paddingModifier
                )
                .size(checkboxSize)
                .selectable(
                    selected = checked,
                    enabled = enabled,
                    role = Role.Checkbox,
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        bounded = false,
                        radius = 24.dp
                    ),
                    onClick = { onCheckedChange(checked.not()) })
                .background(
                    color = backgroundAnimation.value,
                    shape = RoundedCornerShape(checkBoxRadius)
                )
                .then(
                    checkedBorderModifier
                ),
            contentAlignment = Alignment.Center
        ) {
            GfIcon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.OutlinedBold.CheckLine,
                contentDescription = "Check-Line Icon",
                tint = iconAnimation.value
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

private val checkBoxRadius = 6.dp
private val checkboxSize = 24.dp