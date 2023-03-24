package com.example.application.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application.R
import com.example.application.ui.BottomNavigation
import com.example.application.ui.EmptyScreen
import com.example.application.ui.bottomsheet.BottomSheetScreen
import com.example.application.ui.button.ContainerButtonScreen
import com.example.application.ui.button.TextButtonScreen
import com.example.application.ui.chip.ChipScreen
import com.example.application.ui.control.ControlScreen
import com.example.application.ui.dialog.DialogScreen
import com.example.application.ui.dropdown.DropdownScreen
import com.example.application.ui.textfield.TextFieldScreen
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.ui.typography.TypographyScreen
import com.example.application.util.ThemedPreview
import co.seedglobal.design.component.SeedBottomSheetScaffold
import co.seedglobal.design.component.SeedBottomSheetValue
import co.seedglobal.design.component.SeedTopBar
import co.seedglobal.design.component.SeedTopBarDefaults
import co.seedglobal.design.component.rememberSeedBarState
import co.seedglobal.design.component.rememberSeedBottomSheetState
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    val menuItems = listOf(
        DrawerScreens.Typography,
        DrawerScreens.ColorScreen,
        DrawerScreens.ContainerButton,
        DrawerScreens.TextButton,
        DrawerScreens.TextField,
        DrawerScreens.Chip,
        DrawerScreens.Control,
        DrawerScreens.BottomSheet,
        DrawerScreens.Dialog,
        DrawerScreens.Dropdown
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(menuItems[0]) }
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    NavDrawer(
        drawerState = drawerState,
        items = menuItems,
        selectedItem = selectedItem.value,
        scope = scope,
        onItemSelected = {
            selectedItem.value = it
            navController.navigate(it.route)
        },
        content = {
            var bottomSheetContent by remember {
                mutableStateOf<@Composable () -> Unit>({
                    Box(Modifier.size(1.dp))
                })
            }
            val bottomSheetState = rememberSeedBottomSheetState(SeedBottomSheetValue.Hidden)
            var isFixedBottomSheet by remember { mutableStateOf(false) }
            val topBarState = rememberSeedBarState()
            val bottomBarState = rememberSeedBarState()

            SeedBottomSheetScaffold(
                topBar = {
                    SeedTopBar(
                        title = stringResource(id = R.string.app_name),
                        titleAlignment = Alignment.CenterStart,
                        onNavigationClick = { scope.launch { drawerState.open() } },
                        hideWhileScrollUp = true,
                        state = topBarState,
                    )
                },
                bottomBar = {
                    BottomNavigation(
                        navController = navController,
                        state = bottomBarState,
                        hideWhileScrollUp = true,
                    )
                },
                sheetContent = { bottomSheetContent() },
                sheetState = bottomSheetState,
                isFixed = isFixedBottomSheet,
            ) {
                NavHost(
                    navController = navController,
                    startDestination = menuItems.first().route
                ) {
                    menuItems.forEach { menu ->
                        menu.composable(
                            navGraphBuilder = this,
                            onScrollChange = { isScrollUp ->
                                if (isScrollUp) {
                                    topBarState.hide(true)
                                    bottomBarState.hide(true)
                                } else {
                                    topBarState.show(true)
                                    bottomBarState.show(true)
                                }
                            },
                            onShowBottomSheet = { content, isFixed ->
                                bottomSheetContent = content
                                isFixedBottomSheet = isFixed
                                scope.launch {
                                    bottomSheetState.show()
                                }
                            }
                        )
                    }
                    composable("cart") {
                        EmptyScreen(title = "Cart")
                    }
                    composable("delete") {
                        EmptyScreen(title = "Delete")
                    }
                }
            }
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
        onScrollChange: ((isScrollUp: Boolean) -> Unit)? = null,
        onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)? = null,
    )

    object Typography : DrawerScreens(title = "Typography", route = "Typography") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                TypographyScreen { isScrollUp ->
                    onScrollChange?.invoke(isScrollUp)
                }
            }
        }
    }

    object ColorScreen : DrawerScreens(title = "Color", route = "Color") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                EmptyScreen()
            }
        }
    }

    object ContainerButton : DrawerScreens(title = "ContainerButton", route = "ContainerButton") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                ContainerButtonScreen()
            }
        }
    }

    object TextButton : DrawerScreens(title = "TextButton", route = "TextButton") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                TextButtonScreen()
            }
        }
    }

    object Chip : DrawerScreens(title = "Chip", route = "Chip") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                ChipScreen()
            }
        }
    }

    object TextField : DrawerScreens(title = "TextField", route = "TextField") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                TextFieldScreen()
            }
        }
    }

    object Control : DrawerScreens(title = "Control", route = "Control") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                ControlScreen()
            }
        }
    }

    object BottomSheet : DrawerScreens(title = "BottomSheet", route = "BottomSheet") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                BottomSheetScreen(
                    onShowBottomSheet = { content, isFixed ->
                        onShowBottomSheet?.invoke(content, isFixed)
                    }
                )
            }
        }
    }

    object Dialog : DrawerScreens(title = "Dialog", route = "Dialog") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: @Composable () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                DialogScreen()
            }
        }

    }

    object Dropdown : DrawerScreens(title = "Dropdown", route = "Dropdown") {
        override fun composable(
            navGraphBuilder: NavGraphBuilder,
            onScrollChange: ((isScrollUp: Boolean) -> Unit)?,
            onShowBottomSheet: ((content: () -> Unit, isFixed: Boolean) -> Unit)?,
        ) {
            navGraphBuilder.composable(route) {
                DropdownScreen()
            }
        }
    }
}


@ThemedPreview
@Composable
@ExperimentalMaterial3Api
fun HomeScreenPreview() {
    SeedSampleTheme {
        HomeScreen()
    }
}
