package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun GFDialog(
    modifier: Modifier = Modifier,
    itemVisible: Boolean,
    onDismissRequest: () -> Unit,
    buttonContent: @Composable (() -> Unit) = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    if (itemVisible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column {
                    content()
                    buttonContent()
                }
            }
        }
    }
}