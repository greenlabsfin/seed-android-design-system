package com.example.application.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.SeedButton
import com.greenlabsfin.design.component.SeedButtonDefaults
import com.greenlabsfin.design.component.SeedCountDefaults
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.gray60
import com.greenlabsfin.design.core.color.red80

@Composable
fun ContainerButtonScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(16.dp)) {
        SeedText(text = "Primary", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.containerPrimary(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward,
            count = 2,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.containerPrimary())
        )
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.outlinePrimary(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward,
            count = 2,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.outlinePrimary())
        )
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.tintPrimary(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward,
            count = 22,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.tintPrimary())
        )
        Spacer(modifier = Modifier.height(10.dp))
        SeedText(text = "Neutral", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.outlineNeutral(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.tintNeutral(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        Spacer(modifier = Modifier.height(10.dp))
        SeedText(text = "Negative", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.containerNegative(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.Colors.tintNegative(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        Spacer(modifier = Modifier.height(10.dp))
        SeedText(text = "Custom", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            height = SeedButton.Height.Small,
            colors = SeedButtonDefaults.buttonColors(backgroundColor = red80, contentColor = gray60),
            onClick = {},
            leftIcon = Icons.Outlined.Home,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowDropDown
        )
    }
}

@ThemedPreview
@Composable
fun ContainerButtonScreenPreview() {
    SeedSampleTheme {
        ContainerButtonScreen()
    }
}
