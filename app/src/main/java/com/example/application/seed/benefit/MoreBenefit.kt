package com.example.application.seed.benefit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.component.SeedTopBarLayout
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun MoreBenefitScreen(
    navController: NavController,
) {
    Surface(color = SeedTheme.colorScheme.container.background) {
        SeedTopBarLayout(
            title = "More Benefit",
            navigationIcon = Icons.Default.ArrowBack,
            onNavigationClick = {
                navController.navigateUp()
            }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SeedText(text = "More Benefit", style = SeedTheme.typoScheme.headline.mediumBold)
            }
        }
    }
}
