package com.example.application.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.application.R
import com.example.application.ui.EmptyScreen
import com.example.application.ui.bottomsheet.BottomSheetScreen
import com.example.application.ui.button.ContainerButtonScreen
import com.example.application.ui.button.TextButtonScreen
import com.example.application.ui.chip.ChipScreen
import com.example.application.ui.control.ControlScreen
import com.example.application.ui.textfield.TextFieldScreen
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.ui.typography.TypographyScreen
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.core.GfTheme
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    val menuItems = listOf(
        DrawerMenu.Typography,
        DrawerMenu.Color,
        DrawerMenu.ContainerButton,
        DrawerMenu.TextButton,
        DrawerMenu.TextField,
        DrawerMenu.Chip,
        DrawerMenu.Control,
        DrawerMenu.BottomSheet,
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(menuItems[0]) }
    val scope = rememberCoroutineScope()
    Scaffold(
        content = { paddingValues ->
            NavDrawer(
                modifier = Modifier
                    .background(GfTheme.colorScheme.container.background)
                    .padding(paddingValues),
                drawerState = drawerState,
                items = menuItems,
                selectedItem = selectedItem.value,
                scope = scope,
                onItemSelected = { selectedItem.value = it },
                content = {
                    Column {
                        TopBar(
                            title = stringResource(id = R.string.app_name),
                            navigationIcon = Icons.Filled.Menu,
                            onIconClick = {
                                scope.launch { drawerState.open() }
                            }
                        )
                        when (selectedItem.value) {
                            is DrawerMenu.Typography -> TypographyScreen()
                            is DrawerMenu.TextField -> TextFieldScreen()
                            is DrawerMenu.ContainerButton -> ContainerButtonScreen()
                            is DrawerMenu.TextButton -> TextButtonScreen()
                            is DrawerMenu.Control -> ControlScreen()
                            is DrawerMenu.Chip -> ChipScreen()
                            is DrawerMenu.BottomSheet -> BottomSheetScreen()
                            else -> EmptyScreen()
                        }
                    }
                }
            )
        }
    )
}

sealed class DrawerMenu : NavDrawerItem {
    override val icon: ImageVector? = null

    object Typography : DrawerMenu() {
        override val title: String = "Typography"
    }

    object Color : DrawerMenu() {
        override val title: String = "Color"
    }

    object ContainerButton : DrawerMenu() {
        override val title: String = "ContainerButton"
    }

    object TextButton : DrawerMenu() {
        override val title: String = "TextButton"
    }

    object Chip : DrawerMenu() {
        override val title: String = "Chip"
    }

    object TextField : DrawerMenu() {
        override val title: String = "TextField"
    }

    object Control : DrawerMenu() {
        override val title: String = "Controls"
    }

    object BottomSheet : DrawerMenu() {
        override val title: String
            get() = "BottomSheet"
    }
}


@ThemedPreview
@Composable
@ExperimentalMaterial3Api
fun HomeScreenPreview() {
    GFSampleTheme {
        HomeScreen()
    }
}
