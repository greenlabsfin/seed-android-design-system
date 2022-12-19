package com.example.application.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.application.R
import com.example.application.ui.EmptyScreen
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.ui.typography.TypographyScreen
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    val menuItems = listOf(
        DrawerMenu.Typography,
        DrawerMenu.Color,
        DrawerMenu.ContainerButton,
        DrawerMenu.TextButton,
        DrawerMenu.Chip
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(menuItems[0]) }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            NavDrawer(
                modifier = Modifier.padding(paddingValues),
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
                            else -> EmptyScreen()
                        }
                    }
                }
            )

        }
    )
}

sealed interface DrawerMenu : NavDrawerItem {
    object Typography : DrawerMenu {
        override val icon: ImageVector?
            get() = null
        override val title: String
            get() = "Typography"
    }

    object Color : DrawerMenu {
        override val icon: ImageVector?
            get() = null
        override val title: String
            get() = "Color"
    }

    object ContainerButton : DrawerMenu {
        override val icon: ImageVector?
            get() = null
        override val title: String
            get() = "ContainerButton"
    }

    object TextButton : DrawerMenu {
        override val icon: ImageVector?
            get() = null
        override val title: String
            get() = "TextButton"
    }

    object Chip : DrawerMenu {
        override val icon: ImageVector?
            get() = null
        override val title: String
            get() = "Chip"
    }
}


@Preview
@Composable
@ExperimentalMaterial3Api
fun HomeScreenPreview() {
    GFSampleTheme {
        HomeScreen()
    }
}
