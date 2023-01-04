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
import com.greenlabsfin.design.component.SeedBarState
import com.greenlabsfin.design.component.SeedBottomNavigation
import com.greenlabsfin.design.component.SeedBottomNavigationItem
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.component.icons.Icons
import com.greenlabsfin.design.component.icons.filled.Delete
import com.greenlabsfin.design.component.icons.regular.Delete
import com.greenlabsfin.design.component.rememberSeedBarState
import com.greenlabsfin.design.component.toPainter

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
