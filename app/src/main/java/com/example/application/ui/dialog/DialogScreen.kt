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
import com.greenlabsfin.design.component.GFHeight

@Composable
fun DialogScreen() {
    var defaultDialogVisibility by remember { mutableStateOf(false) }
    var negativeDialogVisibility by remember { mutableStateOf(false) }
    var negativeEvenDialogVisibility by remember { mutableStateOf(false) }
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

            GFButton(
                height = GFHeight.Medium,
                colors = GFButton.Style.containerPrimary,
                text = "네거티브 균등 다이얼로그"
            ) {
                negativeEvenDialogVisibility = negativeEvenDialogVisibility.not()
            }
        }
    }

    GFDialog(
        itemVisible = defaultDialogVisibility,
        onDismissRequest = {
            defaultDialogVisibility = defaultDialogVisibility.not()
        },
        title = "타이틀",
        message = "메시지"
    ) {
        GFButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Primary",
            height = GFHeight.Medium,
            colors = GFButton.Style.containerPrimary
        ) {
            defaultDialogVisibility = defaultDialogVisibility.not()
        }
    }

    GFDialog(
        itemVisible = negativeDialogVisibility,
        onDismissRequest = {
            negativeDialogVisibility = negativeDialogVisibility.not()
        },
        title = "타이틀",
        message = "메시지"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GFButton(
                modifier = Modifier.weight(1f),
                text = "Secondary",
                height = GFHeight.Medium,
                colors = GFButton.Style.tintNeutral
            ) {
                negativeDialogVisibility = negativeDialogVisibility.not()
            }

            GFButton(
                modifier = Modifier.weight(2f),
                text = "Primary",
                height = GFHeight.Medium,
                colors = GFButton.Style.containerPrimary
            ) {
                negativeDialogVisibility = negativeDialogVisibility.not()
            }
        }
    }


    GFDialog(
        itemVisible = negativeEvenDialogVisibility,
        onDismissRequest = {
            negativeEvenDialogVisibility = negativeEvenDialogVisibility.not()
        },
        title = "타이틀",
        message = "메시지"
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            GFButton(
                modifier = Modifier.weight(1f),
                text = "Secondary",
                height = GFHeight.Medium,
                colors = GFButton.Style.tintNeutral
            ) {
                negativeEvenDialogVisibility = negativeEvenDialogVisibility.not()
            }
            GFButton(
                modifier = Modifier.weight(1f),
                text = "Primary",
                height = GFHeight.Medium,
                colors = GFButton.Style.containerPrimary
            ) {
                negativeEvenDialogVisibility = negativeEvenDialogVisibility.not()
            }


        }
    }
}