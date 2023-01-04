package com.example.application.ui.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.application.ui.theme.GFSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.SeedTextButton
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun TextButtonScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SeedTextButton(
            text = "활성화 XS 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = SeedTextButton.Style.XSmall,
        ) {}

        SeedTextButton(
            text = "비활성화 XS 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = SeedTextButton.Style.XSmall,
            enabled = false
        ) {}

        SeedTextButton(
            text = "활성화 SM 버튼",
            color = SeedTheme.colorScheme.contents.neutralSecondary,
            rightIcon = Icons.Filled.KeyboardArrowRight,
            style = SeedTextButton.Style.Small,
        ) {}

        SeedTextButton(
            text = "비활성화 SM 버튼",
            color = SeedTheme.colorScheme.contents.neutralSecondary,
            rightIcon = Icons.Filled.KeyboardArrowRight,
            style = SeedTextButton.Style.Small,
            enabled = false
        ) {}

        SeedTextButton(
            text = "활성화 MD 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = SeedTextButton.Style.Medium,
        ) {}

        SeedTextButton(
            text = "비활성화 MD 버튼",
            color = SeedTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = SeedTextButton.Style.Medium,
            enabled = false
        ) {}
    }
}

@ThemedPreview
@Composable
fun TextButtonScreenPreview() {
    GFSampleTheme {
        TextButtonScreen()
    }
}
