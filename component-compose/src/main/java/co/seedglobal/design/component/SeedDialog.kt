package co.seedglobal.design.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import co.seedglobal.design.core.SeedTheme

@Composable
fun SeedDialog(
    modifier: Modifier = Modifier,
    color: Color = SeedTheme.colorScheme.container.background,
    itemVisible: Boolean,
    onDismissRequest: () -> Unit,
    buttonContent: @Composable (() -> Unit) = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    if (itemVisible) {
        Dialog(onDismissRequest = onDismissRequest) {
            SeedSurface(
                modifier = modifier.fillMaxWidth(),
                color = color,
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
