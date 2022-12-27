package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.GfTheme


@Suppress("UNUSED")
object GFDialogDefaults {

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
        fun SinglePrimary(
            buttonText: String = "Primary",
            onPositiveButtonClicked: () -> Unit) {
            GFButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        buttonPaddingModifier
                    ),
                height = GFHeight.Medium,
                colors = GFButton.Style.containerPrimary,
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
                GFButton(
                    modifier = Modifier.weight(1f),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.tintNeutral,
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                GFButton(
                    modifier = Modifier.weight(2f),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.containerPrimary,
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
                GFButton(
                    modifier = Modifier.weight(1f),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.tintNeutral,
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                GFButton(
                    modifier = Modifier.weight(2f),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.containerPrimary,
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
                GFButton(
                    modifier = Modifier.fillMaxWidth(),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.tintNeutral,
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                GFButton(
                    modifier = Modifier.fillMaxWidth(),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.containerPrimary,
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
                GFButton(
                    modifier = Modifier.fillMaxWidth(),
                    height = GFHeight.Medium,
                    colors = GFButton.Style.tintNeutral,
                    text = negativeText,
                    onClick = onNegativeButtonClicked
                )

                GfTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    color = GfTheme.colorScheme.contents.primary,
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
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        contentPaddingModifier
                    ),
                contentAlignment = Alignment.TopStart
            ) {
                GfText(text = title, style = GfTheme.typoScheme.headline.mediumBold)
            }
        }

        @Composable
        fun DefaultText(
            title: String,
            message: String,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        contentPaddingModifier
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GfText(
                    text = title,
                    style = GfTheme.typoScheme.headline.mediumBold,
                    color = GfTheme.colorScheme.contents.neutralPrimary
                )
                GfText(
                    text = message,
                    style = GfTheme.typoScheme.body.mediumRegular,
                    color = GfTheme.colorScheme.contents.neutralSecondary
                )
            }
        }
    }
}