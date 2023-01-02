package com.example.application.ui.dropdown

import android.util.Log
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
import com.greenlabsfin.design.component.DropdownAlignment
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfDropDown
import com.greenlabsfin.design.component.GfIcon
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.GfTextButton
import com.greenlabsfin.design.core.GfTheme

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

        Text(text = "Filled Dropdown", style = GfTheme.typoScheme.headline.largeBold)
        GfDropDown(modifier = Modifier.fillMaxWidth(),
            isExpanded = dropdownExpanded,
            placeholder = {
                GFButton(
                    modifier = Modifier.fillMaxWidth(),
                    height = GFHeight.Large,
                    colors = GFButton.Style.outlineNeutral,
                    rightIcon = Icons.Filled.ArrowDropDown,
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

        Text(text = "Text Dropdown", style = GfTheme.typoScheme.headline.largeBold)

        GfDropDown(
            isExpanded = textExpanded,
            placeholder = {
                GfTextButton(text = selectedTextDropdownItem,
                    rightIcon = if (textExpanded) Icons.Filled.ArrowDropDown else Icons.Filled.ThumbUp) {
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

        Text(text = "Icon Dropdown", style = GfTheme.typoScheme.headline.largeBold)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            GfDropDown(
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
                alignment = DropdownAlignment.End
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
                Log.d("####", "Icon Selected")
                itemSelected()
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GfIcon(imageVector = Icons.Filled.Face, contentDescription = "Face Icon")
        GfText(text = title, style = GfTheme.typoScheme.body.mediumRegular)
    }
}

