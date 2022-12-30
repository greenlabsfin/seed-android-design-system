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
import com.greenlabsfin.design.component.GfBarState
import com.greenlabsfin.design.component.GfBottomNavigation
import com.greenlabsfin.design.component.GfBottomNavigationItem
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.icons.Icons
import com.greenlabsfin.design.component.icons.filled.Delete
import com.greenlabsfin.design.component.icons.regular.Delete
import com.greenlabsfin.design.component.rememberGfBarState
import com.greenlabsfin.design.component.toPainter

@Composable
fun BottomNavigation(
    hideWhileScrollUp: Boolean = false,
    state: GfBarState = rememberGfBarState(false),
    navController: NavController?,
) {
    GfBottomNavigation(
        state = state,
        hideWhileScrollUp = hideWhileScrollUp,

        ) {
        var selected by remember { mutableStateOf(0) }
        GfBottomNavigationItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            selectedIcon = Icons.Filled.Delete.toPainter(),
            unselectedIcon = Icons.OutlinedRegular.Delete.toPainter(),
            contentDescription = "",
            badge = true,
            label = { GfText(text = "First") },
        )
        GfBottomNavigationItem(
            selected = selected == 1,
            onClick = {
                selected = 1
                navController?.navigate("delete")
            },
            selectedIcon = Icons.Filled.Delete.toPainter(),
            unselectedIcon = Icons.OutlinedRegular.Delete.toPainter(),
            contentDescription = "",
            label = { GfText(text = "Delete") },
        )
        GfBottomNavigationItem(
            selected = selected == 2,
            onClick = {
                selected = 2
                navController?.navigate("cart")
            },
            selectedIcon = SampleIcons.Filled.Cart.toPainter(),
            unselectedIcon = SampleIcons.OutlinedRegular.Cart.toPainter(),
            contentDescription = "",
            count = 2,
            label = { GfText(text = "Cart") },
        )
    }
}
