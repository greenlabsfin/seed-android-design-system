package com.example.application.seed.benefit

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.example.application.seed.SeedDestination
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation


fun NavGraphBuilder.benefitNavGraph(
    route: String,
    navController: NavHostController,
    onShowBottomSheet: (bottomSheetContent: @Composable () -> Unit, isFixed: Boolean) -> Unit,
) {
    navigation(
        route = route,
        startDestination = BenefitMain.route
    ) {
        composable(
            route = BenefitMain.route
        ) {
            BenefitScreen(
                navController = navController,
                onShowBottomSheet = onShowBottomSheet
            )
        }
        composable(
            route = MoreBenefit.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = TweenSpec(),
                    initialOffsetY = { it / 2 }
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = TweenSpec(),
                    targetOffsetY = { it / 2 }
                )
            }
        ) {
            MoreBenefitScreen(navController)
        }
    }
}


object BenefitMain : SeedDestination {
    override val route: String
        get() = "benefitMain"
}

object MoreBenefit : SeedDestination {
    override val route: String
        get() = "moreBenefit"
}