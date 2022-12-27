package com.example.application.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application.ui.EmptyScreen
import com.example.application.ui.bottomsheet.BottomSheetScreen
import com.example.application.ui.button.ContainerButtonScreen
import com.example.application.ui.button.TextButtonScreen
import com.example.application.ui.chip.ChipScreen
import com.example.application.ui.control.ControlScreen
import com.example.application.ui.dialog.DialogScreen
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
        DrawerScreens.Typography,
        DrawerScreens.Color,
        DrawerScreens.ContainerButton,
        DrawerScreens.TextButton,
        DrawerScreens.TextField,
        DrawerScreens.Chip,
        DrawerScreens.Control,
        DrawerScreens.BottomSheet,
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(menuItems[0]) }
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
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
                onItemSelected = {
                    selectedItem.value = it
                    navController.navigate(it.route)
                },
                content = {
                    NavHost(
                        navController = navController,
                        startDestination = menuItems.first().route
                    ) {
                        menuItems.forEach { menu ->
                            menu.composable(
                                navGraphBuilder = this,
                                onNavigationClick = { scope.launch { drawerState.open() } }
                            )
                        }
                    }
                }
            )
        }
    )
}

@ExperimentalMaterial3Api
sealed class DrawerScreens(
    override val title: String,
    val route: String,
) : NavDrawerItem {
    abstract fun composable(
        navGraphBuilder: NavGraphBuilder,
        onNavigationClick: () -> Unit = {},
    )

    object Typography : DrawerScreens(title = "Typography", route = "Typography") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                TypographyScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object Color : DrawerScreens(title = "Color", route = "Color") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                EmptyScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object ContainerButton : DrawerScreens(title = "ContainerButton", route = "ContainerButton") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                ContainerButtonScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object TextButton : DrawerScreens(title = "TextButton", route = "TextButton") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                TextButtonScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object Chip : DrawerScreens(title = "Chip", route = "Chip") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                ChipScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object TextField : DrawerScreens(title = "TextField", route = "TextField") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                TextFieldScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object Control : DrawerScreens(title = "Control", route = "Control") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                ControlScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object BottomSheet : DrawerScreens(title = "BottomSheet", route = "BottomSheet") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                BottomSheetScreen(onNavigationClick = onNavigationClick)
            }
        }
    }

    object Dialog: DrawerScreens(title = "Dialog", route = "Dialog") {
        override fun composable(navGraphBuilder: NavGraphBuilder, onNavigationClick: () -> Unit) {
            navGraphBuilder.composable(route) {
                DialogScreen(onNavigationClick = onNavigationClick)
            }
        }

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
