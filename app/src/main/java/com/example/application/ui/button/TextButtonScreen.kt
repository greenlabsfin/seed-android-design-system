package com.example.application.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.util.ThemedPreview
import co.seedglobal.design.component.SeedTextButton
import co.seedglobal.design.component.SeedTextButtonDefaults
import co.seedglobal.design.component.toPainter
import co.seedglobal.design.core.SeedTheme

@Composable
fun TextButtonScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SeedTextButton(
            text = "활성화 XS 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            trailingIcon = Icons.Filled.ArrowDropDown.toPainter(),
            style = SeedTextButtonDefaults.XSmall,
        ) {}

        SeedTextButton(
            text = "비활성화 XS 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            trailingIcon = Icons.Filled.ArrowDropDown.toPainter(),
            style = SeedTextButtonDefaults.XSmall,
            enabled = false
        ) {}

        SeedTextButton(
            text = "활성화 SM 버튼",
            color = SeedTheme.colorScheme.contents.neutralSecondary,
            trailingIcon = Icons.Filled.KeyboardArrowRight.toPainter(),
            style = SeedTextButtonDefaults.Small,
        ) {}

        SeedTextButton(
            text = "비활성화 SM 버튼",
            color = SeedTheme.colorScheme.contents.neutralSecondary,
            trailingIcon = Icons.Filled.KeyboardArrowRight.toPainter(),
            style = SeedTextButtonDefaults.Small,
            enabled = false
        ) {}

        SeedTextButton(
            text = "활성화 MD 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            trailingIcon = Icons.Filled.ArrowDropDown.toPainter(),
            style = SeedTextButtonDefaults.Medium,
        ) {}

        SeedTextButton(
            text = "비활성화 MD 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            trailingIcon = Icons.Filled.ArrowDropDown.toPainter(),
            style = SeedTextButtonDefaults.Medium,
            enabled = false
        ) {}
    }
}

@ThemedPreview
@Composable
fun TextButtonScreenPreview() {
    SeedSampleTheme {
        Surface(color = SeedTheme.colorScheme.container.background) {
            TextButtonScreen()
        }
    }
}
