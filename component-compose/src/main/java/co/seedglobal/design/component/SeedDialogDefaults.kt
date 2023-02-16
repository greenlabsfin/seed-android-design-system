package co.seedglobal.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

    private val buttonPaddingModifier =
        Modifier.padding(
            start = 20.dp,
            end = 20.dp,
            bottom = 20.dp
        )

    private val contentPaddingModifier =
        Modifier.padding(
            top = 24.dp,
            start = 20.dp,
            end = 20.dp,
            bottom = 16.dp,
        )

    object Buttons {
        @Composable
        fun Vertical(
            buttonContent: @Composable ColumnScope.() -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
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
            SeedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
                size = SeedButton.Size.Medium,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                text = buttonText,
                onClick = onPositiveButtonClicked
            )
        }

        @Composable
        fun DoubleHorizontalPrimary(
            negativeText: String = "Secondary",
            positiveText: String = "Primary",
            onNegativeButtonClicked: () -> Unit,
            onPositiveButtonClicked: () -> Unit,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.weight(1f),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.tintNeutral(),
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                SeedButton(
                    modifier = Modifier.weight(2f),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = positiveText,
                    onClick = onPositiveButtonClicked
                )
            }
        }

        @Composable
        fun DoubleHorizontalEven(
            negativeText: String = "Secondary",
            positiveText: String = "Primary",
            onNegativeButtonClicked: () -> Unit,
            onPositiveButtonClicked: () -> Unit,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.weight(1f),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.tintNeutral(),
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                SeedButton(
                    modifier = Modifier.weight(2f),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = positiveText,
                    onClick = onPositiveButtonClicked
                )
            }
        }

        @Composable
        fun DoubleVertical(
            negativeText: String = "Secondary",
            positiveText: String = "Primary",
            onNegativeButtonClicked: () -> Unit,
            onPositiveButtonClicked: () -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.tintNeutral(),
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.containerPrimary(),
                    text = positiveText,
                    onClick = onPositiveButtonClicked
                )
            }
        }

        @Composable
        fun DoubleVerticalText(
            negativeText: String = "Secondary",
            positiveText: String = "Primary",
            onNegativeButtonClicked: () -> Unit,
            onPositiveButtonClicked: () -> Unit,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Medium,
                    colors = SeedButtonDefaults.Colors.tintNeutral(),
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                SeedTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    color = SeedTheme.colorScheme.contents.primary,
                    text = positiveText,
                    onClick = onPositiveButtonClicked
                )
            }
        }
    }

    object Contents {
        @Composable
        fun OnlyTitle(
            title: String,
            showClose: Boolean = false,
            onCloseClick: () -> Unit = {},
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        contentPaddingModifier
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    SeedText(
                        modifier = Modifier.weight(1f),
                        text = title,
                        style = SeedTheme.typoScheme.headline.mediumBold,
                        color = SeedTheme.colorScheme.contents.neutralPrimary
                    )
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
            }
        }

        @Composable
        fun DefaultText(
            title: String,
            message: String,
            showClose: Boolean = false,
            onCloseClick: () -> Unit = {},
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        contentPaddingModifier
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    SeedText(
                        modifier = Modifier.weight(1f),
                        text = title,
                        style = SeedTheme.typoScheme.headline.mediumBold,
                        color = SeedTheme.colorScheme.contents.neutralPrimary
                    )
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
                SeedText(
                    text = message,
                    style = SeedTheme.typoScheme.body.mediumRegular,
                    color = SeedTheme.colorScheme.contents.neutralSecondary
                )
            }
        }
    }
}
