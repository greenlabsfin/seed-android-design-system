package com.greenlabsfin.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.greenlabsfin.design.core.color.red60

@Composable
fun GfDropDown(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    placeholder: @Composable () -> Unit,
    items: @Composable ColumnScope.() -> Unit,
    fillPlaceholderWidth: Boolean = true,
    onDismiss: () -> Unit = {},
) {
    val density = LocalDensity.current
    var contentSize by remember { mutableStateOf(IntSize(0, 0)) }
    var offset by remember { mutableStateOf(0) }
    val widthDp = with(density) { contentSize.width.toDp() }
    var placeholderWidth by remember { mutableStateOf(0) }
    var offsetX by remember { mutableStateOf(0) }

    Box(modifier = modifier) {
        Box(modifier = Modifier
            .onGloballyPositioned {
                contentSize = it.size
                offset = it.size.height + with(density) { 4.dp.roundToPx() }
                placeholderWidth = it.size.width
            }) {
            placeholder()
        }
        if (isExpanded) {
            Popup(
                onDismissRequest = {
                    onDismiss()
                }
            ) {
                Column {
                    with(density) {
                        Box(modifier = Modifier
                            .size(contentSize.width.toDp(),
                                contentSize.height.toDp())
                            .clickable {
                                onDismiss()
                            }
                        )
                    }
                    Surface(
                        modifier = Modifier
                            .then(
                                if (fillPlaceholderWidth) Modifier.width(widthDp)
                                else Modifier
                            )
                            .onGloballyPositioned {
                                offsetX = placeholderWidth - it.size.width
                            },
                        color = red60,
                        elevation = 2.dp,
                        shape = RoundedCornerShape(24.dp)) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            items()
                        }
                    }
                }
            }
        }
    }
}