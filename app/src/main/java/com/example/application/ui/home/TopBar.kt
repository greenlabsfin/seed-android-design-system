package com.example.application.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.application.R
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme
import com.greenlabsfin.design.core.typo.GfTypoScheme

@ExperimentalMaterial3Api
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    onIconClick: () -> Unit = {},
    typoScheme: GfTypoScheme = GfTheme.typoScheme,
    colorScheme: GfColorScheme = GfTheme.colorScheme,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            GfText(
                text = title,
                style = typoScheme.headline.mediumBold
            )
        },
        navigationIcon = {
            navigationIcon?.let { navigationIcon ->
                IconButton(
                    onClick = { onIconClick() }
                ) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIcon.name
                    )
                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorScheme.container.background,
            titleContentColor = colorScheme.contents.neutralPrimary,
            navigationIconContentColor = colorScheme.contents.neutralPrimary
        )
    )
}

@ExperimentalMaterial3Api
@ThemedPreview
@Composable
fun TopBarPreview() {
    GfTheme {
        TopBar(
            title = stringResource(id = R.string.app_name),
            navigationIcon = Icons.Filled.Menu,
            onIconClick = { }
        )
    }
}
