package co.seedglobal.design.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import co.seedglobal.design.core.SeedTheme

@Composable
fun SeedDialog(
    modifier: Modifier = Modifier,
    color: Color = SeedTheme.colorScheme.container.background,
    shape: Shape = RoundedCornerShape(SeedDialogDefaults.defaultRadius),
    contentPadding: PaddingValues = SeedDialogDefaults.contentPadding,
    contentButtonSpace: Dp = 24.dp,
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
                shape = shape,
            ) {
                Column(
                    modifier = Modifier.padding(contentPadding)
                ) {
                    content()
                    Spacer(modifier = Modifier.height(contentButtonSpace))
                    buttonContent()
                }
            }
        }
    }
}
