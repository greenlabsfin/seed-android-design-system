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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.greenlabsfin.design.core.color.white

@Composable
fun GfDropDown(
    modifier: Modifier = Modifier,
    isExpanded: Boolean,
    alignment: DropdownAlignment = DropdownAlignment.Start,
    placeholder: @Composable () -> Unit,
    items: @Composable ColumnScope.() -> Unit,
    fitPlaceholderWidth: Boolean = false,
    onDismiss: () -> Unit = {},
) {
    val density = LocalDensity.current
    var contentSize by remember { mutableStateOf(IntSize(0, 0)) }
    val widthDp = with(density) { contentSize.width.toDp() }
    var offsetX by remember { mutableStateOf(0) }

    Box(modifier = modifier) {
        Box(modifier = Modifier
            .onGloballyPositioned {
                contentSize = it.size
            }) {
            placeholder()
        }
        if (isExpanded) {
            Popup(
                onDismissRequest = {
                    onDismiss()
                },
                offset = IntOffset(when (alignment) {
                    DropdownAlignment.Start -> 0
                    DropdownAlignment.End -> offsetX
                }, 0),
                properties = PopupProperties()
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
                                if (fitPlaceholderWidth) Modifier.width(widthDp)
                                else Modifier
                            )
                            .onGloballyPositioned {
                                offsetX = it.size.width.unaryMinus()
                            },
                        color = white,
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

enum class DropdownAlignment {
    Start, End
}
