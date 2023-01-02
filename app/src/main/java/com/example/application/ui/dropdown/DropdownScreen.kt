package com.example.application.ui.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.greenlabsfin.design.component.GfDropDown

@Composable
fun DropdownScreen() {
    var isExpanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        GfDropDown(
            modifier = Modifier.fillMaxWidth(),
            isExpanded = isExpanded,
            placeholder = {
                PlaceHolder(
                    modifier = Modifier.clickable {
                        isExpanded = true
                    },
                    itemContent = {
                        Text(text = "PlaceHolder")
                    })
            },
            items = {
                ItemContent()
            },
            onDismiss = {
                isExpanded = false
            },
        )
    }
}

@Composable
fun PlaceHolder(
    modifier: Modifier = Modifier,
    itemContent: @Composable () -> Unit,
    expanded: Boolean = false,
) {
    Row {
        itemContent()
    }
}

@Composable
fun ItemContent() {
    Column {

    }
}