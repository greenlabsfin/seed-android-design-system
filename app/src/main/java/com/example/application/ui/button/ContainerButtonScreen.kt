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
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.GFButton
import com.greenlabsfin.design.component.GFHeight
import com.greenlabsfin.design.component.GfCount
import com.greenlabsfin.design.component.GfText
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.gray60
import com.greenlabsfin.design.core.color.red80

@Composable
fun ContainerButtonScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(16.dp)) {
        GfText(text = "Primary", style = GfTheme.typoScheme.headline.largeBold)
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.containerPrimary,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward,
            count = 2,
            countStyle = GfCount.Style.getDefault(buttonColor = GFButton.Style.containerPrimary)
        )
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.outlinePrimary,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward,
            count = 2,
            countStyle = GfCount.Style.getDefault(buttonColor = GFButton.Style.outlinePrimary)
        )
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.tintPrimary,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward,
            count = 22,
            countStyle = GfCount.Style.getDefault(buttonColor = GFButton.Style.tintPrimary)
        )
        Spacer(modifier = Modifier.height(10.dp))
        GfText(text = "Neutral", style = GfTheme.typoScheme.headline.largeBold)
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.outlineNeutral,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.tintNeutral,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        Spacer(modifier = Modifier.height(10.dp))
        GfText(text = "Negative", style = GfTheme.typoScheme.headline.largeBold)
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.containerNegative,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.tintNegative,
            onClick = {},
            leftIcon = Icons.Outlined.Notifications,
            text = "버튼 레이블",
            rightIcon = Icons.Filled.ArrowForward
        )
        Spacer(modifier = Modifier.height(10.dp))
        GfText(text = "Custom", style = GfTheme.typoScheme.headline.largeBold)
        GFButton(
            height = GFHeight.Small,
            colors = GFButton.Style.custom(backgroundColor = red80, contentColor = gray60),
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
    GFSampleTheme {
        ContainerButtonScreen()
    }
}
