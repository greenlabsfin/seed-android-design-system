package com.example.application.ui.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.SeedIcon
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.component.SeedTextField
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun TextFieldScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        var text by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.height(20.dp))
        SeedTextField(
            style = SeedTextField.Style.Outline,
            modifier = Modifier.fillMaxWidth(),
            height = SeedTextField.Size.Medium,
            value = text,
            label = "Medium Outline Error with hint",
            placeholder = "Placeholder",
            isError = text.length > 4,
            errorText = "Error Text",
            onValueChange = { text = it },
            prefix = {
                SeedIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                SeedText(text = "${text.length}/20")
            }
        )

        SeedTextField(
            style = SeedTextField.Style.Outline,
            modifier = Modifier.fillMaxWidth(),
            height = SeedTextField.Size.Small,
            value = text,
            label = "Small Outline Disabled",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                SeedIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                SeedText(text = "${text.length}/20")
            },
            enabled = false
        )

        SeedTextField(
            style = SeedTextField.Style.Outline,
            modifier = Modifier.fillMaxWidth(),
            height = SeedTextField.Size.XSmall,
            value = text,
            label = "XSmall Outline Read Only",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                SeedIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                SeedText(text = "${text.length}/20")
            },
            readOnly = true
        )

        SeedTextField(
            style = SeedTextField.Style.Fill,
            modifier = Modifier.fillMaxWidth(),
            height = SeedTextField.Size.Medium,
            value = text,
            label = "Medium Fill Error with Hint",
            placeholder = "Placeholder",
            isError = text.length > 4,
            errorText = "Error Text",
            onValueChange = { text = it },
            prefix = {
                SeedIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            suffix = {
                SeedText(text = "${text.length}/20")
            }
        )

        SeedTextField(
            style = SeedTextField.Style.Fill,
            modifier = Modifier.fillMaxWidth(),
            height = SeedTextField.Size.Small,
            value = text,
            label = "Small Fill Disabled",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                SeedIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                SeedText(text = "${text.length}/20")
            },
            enabled = false
        )

        SeedTextField(
            style = SeedTextField.Style.Fill,
            modifier = Modifier.fillMaxWidth(),
            height = SeedTextField.Size.XSmall,
            value = text,
            label = "XSmall Fill TextField",
            placeholder = "Placeholder",
            onValueChange = { text = it },
            prefix = {
                SeedIcon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search")
            },
            suffix = {
                SeedText(text = "${text.length}/20")
            },
        )

        SeedTextField(
            style = SeedTextField.Style.Line,
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = "Line Text Field",
            placeholder = "Enter your full name",
            labelTextStyle = SeedTheme.typoScheme.body.smallMedium,
            isError = text.length > 4,
            errorText = "Error Error Error"
        )

        SeedTextField(
            style = SeedTextField.Style.Line,
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = "Line Disabled Text Field",
            placeholder = "Enter your full name",
            labelTextStyle = SeedTheme.typoScheme.body.smallMedium,
            errorText = "Error Error Error",
            enabled = false
        )
        Spacer(modifier = Modifier.height(20.dp))
//        Spacer(modifier = Modifier.height(with(density) { bottomPadding.toDp() }))
    }
}

@ThemedPreview
@Composable
fun TextFieldScreenPreview() {
    SeedSampleTheme {
        Surface(color = SeedTheme.colorScheme.container.background) {
            TextFieldScreen()
        }
    }
}
