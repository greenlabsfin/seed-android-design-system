package co.seedglobal.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.icons.Icons
import co.seedglobal.design.component.icons.regular.Close
import co.seedglobal.design.core.SeedTheme

@Suppress("UNUSED")
object SeedDialogDefaults {
    internal val defaultRadius: Dp = 16.dp

    internal val contentPadding = PaddingValues(
        top = 24.dp,
        bottom = 20.dp,
        start = 20.dp,
        end = 20.dp
    )

    object Buttons {
        @Composable
        fun Vertical(
            buttonContent: @Composable ColumnScope.() -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                buttonContent()
            }
        }

        @Composable
        fun SinglePrimary(
            buttonText: String = "Primary",
            onPositiveButtonClicked: () -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = buttonText,
                    onClick = onPositiveButtonClicked
                )
            }
        }

        @Composable
        fun DoubleHorizontal(
            primaryText: String = "Primary",
            secondaryText: String = "Secondary",
            onPrimaryClick: () -> Unit,
            onSecondaryClick: () -> Unit,
            isEven: Boolean = true,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.weight(1f),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.tintNeutral(),
                    text = secondaryText,
                    onClick = onSecondaryClick
                )

                SeedButton(
                    modifier = Modifier.weight(if (isEven) 1f else 2f),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = primaryText,
                    onClick = onPrimaryClick
                )
            }
        }

        @Composable
        fun DoubleVertical(
            primaryText: String = "Primary",
            secondaryText: String = "Secondary",
            onPrimaryClick: () -> Unit,
            onSecondaryClick: () -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = primaryText,
                    onClick = onPrimaryClick
                )

                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.tintNeutral(),
                    text = secondaryText,
                    onClick = onSecondaryClick
                )
            }
        }

        @Composable
        fun DoubleVerticalText(
            primaryText: String = "Primary",
            secondaryText: String = "Secondary",
            onPrimaryClick: () -> Unit,
            onSecondaryClick: () -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = primaryText,
                    onClick = onPrimaryClick
                )

                SeedTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    color = SeedTheme.colorScheme.contents.primary,
                    text = secondaryText,
                    onClick = onSecondaryClick
                )
            }
        }
    }

    object Contents {
        @Composable
        fun DefaultText(
            title: String? = null,
            message: String? = null,
            showClose: Boolean = false,
            onCloseClick: () -> Unit = {},
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                title?.let {
                    SeedText(
                        text = title,
                        style = SeedTheme.typoScheme.headline.mediumBold,
                        color = SeedTheme.colorScheme.contents.neutralPrimary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (showClose) {
                    SeedIcon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = onCloseClick
                            ),
                        imageVector = Icons.default.Close,
                        contentDescription = "close"
                    )
                }
            }
            if (title != null && message != null) {
                Spacer(modifier = Modifier.height(8.dp))
            }
            message?.let {
                SeedText(
                    text = message,
                    style = SeedTheme.typoScheme.body.mediumRegular,
                    color = SeedTheme.colorScheme.contents.neutralSecondary
                )
            }
        }
    }
}
