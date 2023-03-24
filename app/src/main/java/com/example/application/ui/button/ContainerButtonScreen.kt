package com.example.application.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import co.seedglobal.design.component.SeedButton
import co.seedglobal.design.component.SeedButtonDefaults
import co.seedglobal.design.component.SeedCountDefaults
import co.seedglobal.design.component.SeedText
import co.seedglobal.design.component.toPainter
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.gray60
import co.seedglobal.design.core.color.red80

@Composable
fun ContainerButtonScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SeedText(text = "Primary", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.containerPrimary(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter(),
            count = 2,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.containerPrimary())
        )
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.outlinePrimary(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter(),
            enabled = false,
            count = 2,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.outlinePrimary())
        )
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.tintPrimary(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter(),
            count = 22,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.tintPrimary())
        )
        Spacer(modifier = Modifier.height(10.dp))
        SeedText(text = "Neutral", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.outlineNeutral(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter()
        )
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.tintNeutral(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter()
        )
        Spacer(modifier = Modifier.height(10.dp))
        SeedText(text = "Negative", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.containerNegative(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter()
        )
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.Colors.tintNegative(),
            onClick = {},
            leftIcon = Icons.Outlined.Notifications.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward.toPainter(),
            count = 11,
            countColors = SeedCountDefaults.getByButtonColor(buttonColor = SeedButtonDefaults.Colors.tintNegative())
        )
        Spacer(modifier = Modifier.height(10.dp))
        SeedText(text = "Custom", style = SeedTheme.typoScheme.headline.largeBold)
        SeedButton(
            size = SeedButton.Size.Small,
            colors = SeedButtonDefaults.buttonColors(backgroundColor = red80, contentColor = gray60),
            onClick = {},
            leftIcon = Icons.Outlined.Home.toPainter(),
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowDropDown.toPainter(),
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
