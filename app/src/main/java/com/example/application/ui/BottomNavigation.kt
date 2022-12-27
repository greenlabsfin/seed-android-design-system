package com.example.application.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.application.ui.icons.SampleIcons
import com.example.application.ui.icons.filled.Cart
import com.example.application.ui.icons.regular.Cart
import com.greenlabsfin.design.component.GfBottomNavigation
import com.greenlabsfin.design.component.GfBottomNavigationItem
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.component.icons.Icons
import com.greenlabsfin.design.component.icons.filled.Delete
import com.greenlabsfin.design.component.icons.regular.Delete

@Composable
fun BottomNavigation(
    listState: LazyListState = rememberLazyListState(),
    hideWhileScrollUp: Boolean = false,
    navController: NavController?,
) {
    GfBottomNavigation(
        listState = listState,
        hideWhileScrollUp = hideWhileScrollUp,
    ) {
        var selected by remember { mutableStateOf(0) }
        GfBottomNavigationItem(
            selected = selected == 0,
            onClick = { selected = 0 },
            selectedIcon = Icons.Filled.Delete,
            unselectedIcon = Icons.OutlinedRegular.Delete,
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
            selectedIcon = Icons.Filled.Delete,
            unselectedIcon = Icons.OutlinedRegular.Delete,
            contentDescription = "",
            label = { GfText(text = "Delete") },
        )
        GfBottomNavigationItem(
            selected = selected == 2,
            onClick = {
                selected = 2
                navController?.navigate("cart")
            },
            selectedIcon = SampleIcons.Filled.Cart,
            unselectedIcon = SampleIcons.OutlinedRegular.Cart,
            contentDescription = "",
            count = 2,
            label = { GfText(text = "Cart") },
        )
    }
}
