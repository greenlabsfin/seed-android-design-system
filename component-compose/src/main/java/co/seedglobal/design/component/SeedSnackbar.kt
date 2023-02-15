package co.seedglobal.design.component

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.LocalSeedContentColor
import co.seedglobal.design.core.SeedTheme

object SeedSnackbarDefaults {
    val backgroundColor: Color
        @Composable
        get() = SeedTheme.colorScheme.container.inverse

    val primaryActionColor: Color
        @Composable
        get() = SeedTheme.colorScheme.contents.onInverse
}

@Composable
fun SeedSnackbar(
    snackbarData: SeedSnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    shape: Shape = RoundedCornerShape(10.dp),
    backgroundColor: Color = SeedSnackbarDefaults.backgroundColor,
    contentColor: Color = SeedTheme.colorScheme.contents.onInverse,
    actionColor: Color = SeedSnackbarDefaults.primaryActionColor,
    elevation: Dp = 6.dp,
) {
    Log.d("###", "snackbar composition")
    val actionLabel = snackbarData.actionLabel
    val actionComposable: (@Composable () -> Unit)? = if (actionLabel != null) {
        @Composable {
            SeedTextButton(
                text = actionLabel,
                color = actionColor,
                onClick = { snackbarData.performAction() },
            )
        }
    } else {
        null
    }
    Snackbar(
        modifier = modifier.padding(12.dp),
        content = {
            Row {
                snackbarData.icon?.let { icon ->
                    val color = snackbarData.iconColor ?: LocalSeedContentColor.current
                    SeedIcon(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically),
                        icon = icon,
                        contentDescription = "icon",
                        tint = color
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                }
                SeedText(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    text = snackbarData.message,
                    style = SeedTheme.typoScheme.body.smallMedium,
                    color = contentColor,
                )
            }
        },
        action = actionComposable,
        actionOnNewLine = actionOnNewLine,
        shape = shape,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )
}
