package com.example.application.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.SeedButton
import co.seedglobal.design.component.SeedButtonDefaults
import co.seedglobal.design.component.SeedDialog
import co.seedglobal.design.component.SeedDialogDefaults

@Composable
fun DialogScreen() {
    var defaultDialogVisibility by remember { mutableStateOf(false) }
    var buttonDialogVisibility by remember { mutableStateOf(false) }
    var negativeVisibility by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SeedButton(
                size = SeedButton.Size.Medium,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                text = "기본 다이얼로그"
            ) {
                defaultDialogVisibility = defaultDialogVisibility.not()
            }

            SeedButton(
                size = SeedButton.Size.Medium,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                text = "기본 버튼 다이얼로그"
            ) {
                buttonDialogVisibility = buttonDialogVisibility.not()
            }

            SeedButton(
                size = SeedButton.Size.Medium,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                text = "기본 버튼 다이얼로그 with Negative"
            ) {
                negativeVisibility = negativeVisibility.not()
            }
        }
    }

    SeedDialog(
        itemVisible = defaultDialogVisibility,
        onDismissRequest = {
            defaultDialogVisibility = defaultDialogVisibility.not()
        },
        content = {
            SeedDialogDefaults.Contents.DefaultText(title = "기본 타이틀", message = "기본 메시지")
        }
    )

    SeedDialog(
        itemVisible = buttonDialogVisibility,
        onDismissRequest = {
            buttonDialogVisibility = buttonDialogVisibility.not()
        },
        content = {
            SeedDialogDefaults.Contents.DefaultText(title = "버튼 타이틀")
        },
        buttonContent = {
            SeedDialogDefaults.Buttons.SinglePrimary {
                buttonDialogVisibility = false
            }
        }
    )

    SeedDialog(
        itemVisible = negativeVisibility,
        onDismissRequest = {
            negativeVisibility = negativeVisibility.not()
        },
        content = {
            SeedDialogDefaults.Contents.DefaultText(title = "버튼 타이틀", message = "Negative")
        },
        buttonContent = {
            SeedDialogDefaults.Buttons.DoubleHorizontal(
                onSecondaryClick = {
                    negativeVisibility = false
                },
                onPrimaryClick = {
                    negativeVisibility = false
                },
            )
        },
    )
}
