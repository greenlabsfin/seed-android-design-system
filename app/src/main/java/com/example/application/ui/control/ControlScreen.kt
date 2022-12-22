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
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.control.GFRadioButton
import com.greenlabsfin.design.component.control.GFSwitch
import com.greenlabsfin.design.component.control.SwitchSize
import com.greenlabsfin.design.core.GfTheme

@Composable
fun ControlScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SwitchComponents()
        RadioComponents()
    }
}

@Composable
private fun SwitchComponents() {
    var selectedBigSwitch by remember { mutableStateOf(false) }
    var selectedSmallSwitch by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        GfText(text = "Switch", style = GfTheme.typoScheme.headline.largeBold)
        GFSwitch(
            checked = selectedBigSwitch,
            onCheckedChange = { selectedBigSwitch = selectedBigSwitch.not() },
            text = "Big Switch"
        )

        GFSwitch(
            checked = selectedSmallSwitch,
            onCheckedChange = { selectedSmallSwitch = selectedSmallSwitch.not() },
            switchSize = SwitchSize.Small,
            text = "Small Switch"
        )

        GFSwitch(
            checked = true,
            onCheckedChange = {},
            enabled = false,
            text = "Disabled Checked Switch"
        )

        GFSwitch(
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
        GfText(text = "Radio Button", style = GfTheme.typoScheme.headline.largeBold)
        GFRadioButton(
            selected = radioItem,
            text = "Default Radio Button"
        ) { radioItem = radioItem.not() }

        GFRadioButton(
            selected = boldRadioItem,
            text = "Bold Radio Item",
            textStyle = GfTheme.typoScheme.body.mediumBold
        ) {
            boldRadioItem = boldRadioItem.not()
        }
    }
}