package com.greenlabsfin.design.component.control

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray20
import com.greenlabsfin.design.core.color.gray40

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
    checkedTrackColor: Color = GfTheme.colorScheme.container.primary,
    uncheckedTrackColor: Color = gray40,
    thumbColor: Color = GfTheme.colorScheme.contents.onPrimary,
    onCheckedChange: ((Boolean) -> Unit)? = null,
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
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            // This is called when the user taps on the canvas
                            onCheckedChange?.invoke(checked)
                        }
                    )
                }
        ) {
            // Track
            drawRoundRect(
                color = if (enabled) {
                    if (checked) checkedTrackColor else uncheckedTrackColor
                } else {
                    gray20
                },
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
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = it,
                style = textStyle
            )
        }
    }
}

@Preview("SwitchPreview")
@Composable
fun SwitchPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
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