package com.example.application.ui.control

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.SeedCheckbox
import co.seedglobal.design.component.SeedRadioButton
import co.seedglobal.design.component.SeedSwitch
import co.seedglobal.design.component.SwitchSize
import co.seedglobal.design.core.SeedTheme

@Composable
fun ControlScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SwitchComponents()
        RadioComponents()
        CheckboxComponents()
    }
}

@Composable
private fun SwitchComponents() {
    var selectedBigSwitch by remember { mutableStateOf(false) }
    var selectedSmallSwitch by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SeedText(text = "Switch", style = SeedTheme.typoScheme.headline.largeBold)
        SeedSwitch(
            checked = selectedBigSwitch,
            onCheckedChange = { selectedBigSwitch = selectedBigSwitch.not() },
            text = "Big Switch"
        )

        SeedSwitch(
            checked = selectedSmallSwitch,
            onCheckedChange = { selectedSmallSwitch = selectedSmallSwitch.not() },
            switchSize = SwitchSize.Small,
            text = "Small Switch"
        )

        SeedSwitch(
            checked = true,
            onCheckedChange = {},
            enabled = false,
            text = "Disabled Checked Switch"
        )

        SeedSwitch(
            checked = false,
            onCheckedChange = {},
            enabled = false,
            text = "Disabled Unchecked Switch"
        )
    }
}

@Composable
private fun RadioComponents() {
    var radioItem by remember { mutableStateOf(false) }
    var boldRadioItem by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SeedText(text = "Radio Button", style = SeedTheme.typoScheme.headline.largeBold)
        SeedRadioButton(
            selected = radioItem,
            text = "Default Radio Button"
        ) { radioItem = radioItem.not() }

        SeedRadioButton(
            selected = boldRadioItem,
            text = "Bold Radio Item",
            textStyle = SeedTheme.typoScheme.body.mediumBold
        ) {
            boldRadioItem = boldRadioItem.not()
        }
    }
}

@Composable
private fun CheckboxComponents() {
    var checkboxItem by remember { mutableStateOf(false) }
    var boldCheckboxItem by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SeedText(text = "Checkbox", style = SeedTheme.typoScheme.headline.largeBold)
        SeedCheckbox(
            checked = checkboxItem,
            onCheckedChange = { checkboxItem = it },
            text = "Default Checkbox"
        )

        SeedCheckbox(
            checked = boldCheckboxItem,
            onCheckedChange = { boldCheckboxItem = it },
            text = "Bold Default Checkbox",
            textStyle = SeedTheme.typoScheme.body.mediumBold
        )

        SeedCheckbox(
            checked = true,
            onCheckedChange = {},
            text = "Disabled Checkbox",
            enabled = false
        )
    }
}
