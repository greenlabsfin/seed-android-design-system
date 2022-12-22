package com.example.application.ui.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfFillTextField
import com.greenlabsfin.design.component.GfIcon
import com.greenlabsfin.design.component.GfLineTextField
import com.greenlabsfin.design.component.GfOutlineTextField
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.core.GfTheme

@Composable
fun TextFieldScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var text by remember { mutableStateOf("") }
        GfOutlineTextField(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.Medium,
            value = text,
            label = "Medium Outline Error with hint",
            placeholder = "Placeholder",
            isError = text.length > 4,
            errorText = "Error Text",
            onValueChange = { text = it },
            prefix = {
                GfIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                GfText(text = "${text.length}/20")
            }
        )

        GfOutlineTextField(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.Small,
            value = text,
            label = "Small Outline Disabled",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                GfIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                GfText(text = "${text.length}/20")
            },
            enabled = false
        )

        GfOutlineTextField(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.XSmall,
            value = text,
            label = "XSmall Outline Read Only",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                GfIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                GfText(text = "${text.length}/20")
            },
            readOnly = true
        )

        GfFillTextField(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.Medium,
            value = text,
            label = "Medium Fill Error with Hint",
            placeholder = "Placeholder",
            isError = text.length > 4,
            errorText = "Error Text",
            onValueChange = { text = it },
            prefix = {
                GfIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            suffix = {
                GfText(text = "${text.length}/20")
            }
        )

        GfFillTextField(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.Small,
            value = text,
            label = "Small Fill Disabled",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                GfIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                GfText(text = "${text.length}/20")
            },
            enabled = false
        )

        GfFillTextField(
            modifier = Modifier.fillMaxWidth(),
            height = GFHeight.XSmall,
            value = text,
            label = "XSmall Fill TextField",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                GfIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                GfText(text = "${text.length}/20")
            },
        )

        GfLineTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = "Line Text Field",
            placeholder = "Enter your full name",
            labelTextStyle = GfTheme.typoScheme.body.smallMedium,
            isError = text.length > 4,
            errorText = "Error Error Error"
        )

        GfLineTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = "Line Disabled Text Field",
            placeholder = "Enter your full name",
            labelTextStyle = GfTheme.typoScheme.body.smallMedium,
            errorText = "Error Error Error",
            enabled = false
        )
    }
}

@ThemedPreview
@Composable
fun TextFieldScreenPreview() {
    GFSampleTheme {
        TextFieldScreen()
    }
}
