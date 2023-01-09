package com.example.application.seed

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.application.R
import com.example.application.seed.benefit.BenefitMain
import com.example.application.seed.benefit.benefitNavGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import co.seedglobal.design.component.SeedBottomNavigation
import co.seedglobal.design.component.SeedBottomNavigationItem
import co.seedglobal.design.component.SeedBottomSheetScaffold
import co.seedglobal.design.component.SeedBottomSheetValue
import co.seedglobal.design.component.SeedIcon
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.SeedTopBar
import co.seedglobal.design.component.rememberSeedBottomSheetState
import co.seedglobal.design.core.SeedTheme
import kotlinx.coroutines.launch

@Composable
fun SeedMain() {
    val navController = rememberAnimatedNavController()
    val bottomSheetState = rememberSeedBottomSheetState(SeedBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    var bottomSheetContent by remember { mutableStateOf<@Composable () -> Unit>({}) }
    var isFixed by remember { mutableStateOf(true) }
    val currentRoute = navController.currentRoute().value
    val isFullScreen = when (currentRoute) {
        BenefitMain.route, Pay.route, More.route -> false
        else -> true
    }

    SeedBottomSheetScaffold(
        topBar = {
            if (isFullScreen.not()) {
                SeedTopBar(
                    titleContent = {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "logo"
                        )
                    },
                    titleAlignment = Alignment.CenterStart,
                    trailingContent = {
                        SeedIcon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "profile",
                            tint = Color.Unspecified
                        )
                    },
                    topBarPadding = PaddingValues(horizontal = 20.dp),
                    color = SeedTheme.colorScheme.container.background,
                    hideWhileScrollUp = true,
                )
            }
        },
        bottomBar = {
            if (isFullScreen.not()) {
                SeedBottomNavigation(navController = navController)
            }
        },
        sheetContent = { bottomSheetContent() },
        sheetState = bottomSheetState,
        isFixed = isFixed,
    ) {
        SeedHomeNavGraph(navController = navController,
            onShowBottomSheet = { content, fixed ->
                bottomSheetContent = content
                isFixed = fixed
                scope.launch {
                    bottomSheetState.show()
                }
            },
            onScrollChange = { }
        )
    }
}

@Composable
fun SeedTopBar() {
    SeedTopBar(
        titleContent = {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )
        },
        titleAlignment = Alignment.CenterStart,
        trailingContent = {
            SeedIcon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                tint = Color.Unspecified
            )
        },
        topBarPadding = PaddingValues(horizontal = 20.dp),
        color = SeedTheme.colorScheme.container.background,
        hideWhileScrollUp = true,
    )
}

@Composable
fun SeedHomeNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onScrollChange: (isUp: Boolean) -> Unit = {},
    onShowBottomSheet: (bottomSheetContent: @Composable () -> Unit, isFixed: Boolean) -> Unit,
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = "main"
    ) {
        navigation(
            route = "main",
            startDestination = Benefit.route
        ) {
            benefitNavGraph(
                route = Benefit.route,
                navController = navController,
                onShowBottomSheet = onShowBottomSheet
            )
            composable(route = Pay.route) {
                PayScreen(
                    onScrollChange = onScrollChange
                )
            }
            composable(route = More.route) {
                MoreScreen()
            }
        }
    }
}

@Composable
private fun NavController.currentRoute(): State<String?> {
    val currentRoute = remember { mutableStateOf<String?>(BenefitMain.route) }
    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route
        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return currentRoute
}

@Composable
fun SeedBottomNavigation(
    navController: NavController,
) {
    val bottomMenus = listOf(
        Benefit, Pay, More
    )
    SeedBottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        bottomMenus.forEach { menu ->
            SeedBottomNavigationItem(
                selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == menu.route } == true,
                onClick = {
                    navController.navigate(menu.route) {
                        launchSingleTop = true
                        restoreState = true

                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                selectedIcon = menu.icon(),
                contentDescription = menu.title,
                selectedContentColor = SeedTheme.colorScheme.contents.neutralPrimary,
                unselectedContentColor = SeedTheme.colorScheme.contents.neutralSecondary,
                label = {
                    SeedText(text = menu.title)
                }
            )
        }
    }
}

interface SeedDestination {
    val route: String

}

sealed interface SeedBottomNavItem : SeedDestination {
    val title: String
    val firstRoute: String?

    @Composable
    fun icon(): Painter
}

object Benefit : SeedBottomNavItem {
    override val route: String
        get() = "benefit"

    override val firstRoute: String
        get() = BenefitMain.route

    override val title: String
        get() = "Benefit"

    @Composable
    override fun icon(): Painter = painterResource(id = R.drawable.trackter)
}

object Pay : SeedBottomNavItem {
    override val route: String
        get() = "pay"

    override val title: String
        get() = "Pay"
    override val firstRoute: String?
        get() = null

    @Composable
    override fun icon(): Painter = painterResource(id = R.drawable.moneybag)
}

object More : SeedBottomNavItem {
    override val route: String
        get() = "more"

    override val title: String
        get() = "More"
    override val firstRoute: String?
        get() = null

    @Composable
    override fun icon(): Painter = painterResource(id = R.drawable.menu)
}
