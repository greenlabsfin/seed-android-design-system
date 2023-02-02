package com.example.application.ui.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import co.seedglobal.design.component.SeedDropdown
import co.seedglobal.design.component.SeedIcon
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.SeedTextButton
import co.seedglobal.design.component.toPainter
import co.seedglobal.design.core.SeedTheme

@Composable
fun DropdownScreen() {
    val list = listOf(
        "월등 복숭아 4kg",
        "월등 복숭아 6kg",
        "월등 복숭아 10kg",
        "월등 복숭아 20kg",
    )
    var dropdownExpanded by remember { mutableStateOf(false) }
    var textExpanded by remember { mutableStateOf(false) }
    var iconExpanded by remember { mutableStateOf(false) }

    var selectedDropdownItem by remember { mutableStateOf(list[0]) }
    var selectedTextDropdownItem by remember { mutableStateOf(list[0]) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Filled Dropdown", style = SeedTheme.typoScheme.headline.largeBold)
        SeedDropdown(modifier = Modifier.fillMaxWidth(),
            isExpanded = dropdownExpanded,
            placeholder = {
                SeedButton(
                    modifier = Modifier.fillMaxWidth(),
                    size = SeedButton.Size.Large,
                    colors = SeedButtonDefaults.Colors.outlineNeutral(),
                    rightIcon = Icons.Filled.ArrowDropDown.toPainter(),
                    text = selectedDropdownItem
                ) {
                    dropdownExpanded = dropdownExpanded.not()
                }
            },
            items = {
                ItemContent(items = list) { item ->
                    selectedDropdownItem = list.find { it == item } ?: list[0]
                }
            },
            onDismiss = {
                dropdownExpanded = false
            }
        )

        Text(text = "Text Dropdown", style = SeedTheme.typoScheme.headline.largeBold)

        SeedDropdown(
            isExpanded = textExpanded,
            placeholder = {
                SeedTextButton(text = selectedTextDropdownItem,
                    trailingIcon = if (textExpanded) Icons.Filled.ArrowDropDown.toPainter() else Icons.Filled.ThumbUp.toPainter()) {
                    textExpanded = textExpanded.not()
                }
            },
            items = {
                ItemContent(items = list) { item ->
                    selectedTextDropdownItem = list.find { it == item } ?: list[0]
                }
            },
            onDismiss = {
                textExpanded = false
            }
        )

        Text(text = "Icon Dropdown", style = SeedTheme.typoScheme.headline.largeBold)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SeedDropdown(
                isExpanded = iconExpanded,
                placeholder = {
                    Icon(imageVector = Icons.Filled.Menu,
                        contentDescription = "Icon Meny", modifier = Modifier.clickable {
                            iconExpanded = iconExpanded.not()
                        }
                    )
                },
                items = {
                    ItemContent(items = list) {}
                },
                onDismiss = {
                    iconExpanded = false
                },
                alignment = SeedDropdown.Alignment.End
            )
        }
    }
}

@Composable
fun ItemContent(
    items: List<String>,
    onItemSelected: (String) -> Unit,
) {
    Column {
        items.forEach {
            ListComponent(title = it) {
                onItemSelected(it)
            }
        }
    }
}

@Composable
fun ListComponent(
    title: String,
    itemSelected: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                itemSelected()
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SeedIcon(imageVector = Icons.Filled.Face, contentDescription = "Face Icon")
        SeedText(text = title, style = SeedTheme.typoScheme.body.mediumRegular)
    }
}

