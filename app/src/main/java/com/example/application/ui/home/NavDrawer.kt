package com.example.application.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.SeedColorScheme
import co.seedglobal.design.core.typo.SeedTypoScheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun <T : NavDrawerItem> NavDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    items: List<T> = emptyList(),
    selectedItem: T,
    scope: CoroutineScope = rememberCoroutineScope(),
    onItemSelected: (T) -> Unit,
    colorScheme: SeedColorScheme = SeedTheme.colorScheme,
    typoScheme: SeedTypoScheme = SeedTheme.typoScheme,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        label = {
                            SeedText(
                                text = item.title,
                                style = typoScheme.body.mediumRegular,
                            )
                        },
                        shape = RoundedCornerShape(4.dp),
                        selected = item == selectedItem,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = colorScheme.container.secondary,
                            selectedTextColor = colorScheme.contents.neutralPrimary,
                            selectedIconColor = colorScheme.contents.neutralPrimary,
                        ),
                        onClick = {
                            scope.launch {
                                if (drawerState.isOpen) drawerState.close()
                                else drawerState.open()
                            }
                            onItemSelected(item)
                        }
                    )
                }
            }
        },
        content = content
    )
}

interface NavDrawerItem {
    val title: String
}
