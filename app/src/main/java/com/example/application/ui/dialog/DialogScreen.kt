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
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFDialog
import com.greenlabsfin.design.component.GFDialogDefaults
import com.greenlabsfin.design.component.GFHeight

@Composable
fun DialogScreen() {
    var defaultDialogVisibility by remember { mutableStateOf(false) }
    var negativeDialogVisibility by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GFButton(
                height = GFHeight.Medium,
                colors = GFButton.Style.containerPrimary,
                text = "기본 다이얼로그"
            ) {
                defaultDialogVisibility = defaultDialogVisibility.not()
            }

            GFButton(
                height = GFHeight.Medium,
                colors = GFButton.Style.containerPrimary,
                text = "네거티브 다이얼로그"
            ) {
                negativeDialogVisibility = negativeDialogVisibility.not()
            }
        }
    }

    GFDialog(
        itemVisible = defaultDialogVisibility,
        onDismissRequest = {
            defaultDialogVisibility = defaultDialogVisibility.not()
        },
        content = {
            GFDialogDefaults.Contents.DefaultText(title = "기본 타이틀", message = "기본 메시지")
        }
    )

    GFDialog(
        itemVisible = negativeDialogVisibility,
        onDismissRequest = {
            negativeDialogVisibility = negativeDialogVisibility.not()
        },
        content = {
            GFDialogDefaults.Contents.OnlyTitle(title = "버튼 타이틀")
        },
        buttonContent = {
            GFDialogDefaults.Buttons.SinglePrimary {
                negativeDialogVisibility = false
            }
        }
    )
}