package com.greenlabsfin.design.component.control

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.util.GfPreview
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme

enum class SwitchSize {
    Large,
    Small
}

@Composable
fun GFSwitch(
    checked: Boolean,
    enabled: Boolean = true,
    text: String? = null,
    textStyle: TextStyle = GfTheme.typoScheme.body.mediumRegular,
    switchSize: SwitchSize = SwitchSize.Large,
    colors: ControlColors = GFControl.Colors.switchPrimary,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: (Boolean) -> Unit,
) {

    val switchWidth = when (switchSize) {
        SwitchSize.Large -> 54.dp
        SwitchSize.Small -> 40.dp
    }

    val switchHeight = when (switchSize) {
        SwitchSize.Large -> 32.dp
        SwitchSize.Small -> 24.dp
    }

    val thumbRadius = (switchHeight / 2) - 3.dp

    val trackColor = colors.controlColor(enabled = enabled, selected = checked).value
    val thumbColor = colors.controlDotColor(enabled = true, selected = true).value


    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (checked)
            with(LocalDensity.current) { (switchWidth - thumbRadius - 3.dp).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + 3.dp).toPx() }
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(
            modifier = Modifier
                .size(width = switchWidth, height = switchHeight)
                .selectable(
                    selected = checked,
                    enabled = enabled,
                    role = Role.Switch,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onCheckedChange(checked)
                    }
                )
        ) {
            // Track
            drawRoundRect(
                color = trackColor,
                cornerRadius = CornerRadius(30.dp.toPx()),
            )

            // Thumb
            drawCircle(
                color = thumbColor,
                radius = thumbRadius.toPx(),
                center = Offset(
                    x = animatePosition.value,
                    y = size.height / 2
                )
            )
        }
        text?.let {
            GfText(
                modifier = Modifier.padding(start = 8.dp),
                text = it,
                style = textStyle
            )
        }
    }
}

@GfPreview("SwitchPreview")
@Composable
fun SwitchPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
    GfTheme(
        colorScheme = GfColorScheme.default(isSystemInDarkTheme())
    ) {
        Surface(
            color = GfTheme.colorScheme.container.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GFSwitch(
                    checked = checked,
                    onCheckedChange = { checked = checked.not() },
                    text = "레이블 텍스트"
                )

                GFSwitch(checked = checked,
                    onCheckedChange = { checked = checked.not() },
                    switchSize = SwitchSize.Small,
                    text = "레이블 텍스트"
                )
            }
        }
    }
}
