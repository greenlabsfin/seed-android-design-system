package com.example.application.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.application.ui.icons.SampleIcons
import com.example.application.ui.icons.filled.Cart
import com.example.application.ui.icons.regular.Cart
import co.seedglobal.design.component.SeedBarState
import co.seedglobal.design.component.SeedBottomNavigation
import co.seedglobal.design.component.SeedBottomNavigationItem
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.icons.Icons
import co.seedglobal.design.component.icons.filled.Delete
import co.seedglobal.design.component.icons.regular.Delete
import co.seedglobal.design.component.rememberSeedBarState
import co.seedglobal.design.component.toPainter

@Composable
fun BottomNavigation(
    hideWhileScrollUp: Boolean = false,
    state: SeedBarState = rememberSeedBarState(false),
    navController: NavController?,
) {
    SeedBottomNavigation(
        state = state,
        hideWhileScrollUp = hideWhileScrollUp,

        ) {
        var selected by remember { mutableStateOf(0) }
        SeedBottomNavigationItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            selectedIcon = Icons.Filled.Delete.toPainter(),
            unselectedIcon = Icons.OutlinedRegular.Delete.toPainter(),
            contentDescription = "",
            badge = true,
            label = { SeedText(text = "First") },
        )
        SeedBottomNavigationItem(
            selected = selected == 1,
            onClick = {
                selected = 1
                navController?.navigate("delete")
            },
            selectedIcon = Icons.Filled.Delete.toPainter(),
            unselectedIcon = Icons.OutlinedRegular.Delete.toPainter(),
            contentDescription = "",
            label = { SeedText(text = "Delete") },
        )
        SeedBottomNavigationItem(
            selected = selected == 2,
            onClick = {
                selected = 2
                navController?.navigate("cart")
            },
            selectedIcon = SampleIcons.Filled.Cart.toPainter(),
            unselectedIcon = SampleIcons.OutlinedRegular.Cart.toPainter(),
            contentDescription = "",
            count = 2,
            label = { SeedText(text = "Cart") },
        )
    }
}
