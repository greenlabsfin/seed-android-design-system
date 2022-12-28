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
import com.greenlabsfin.design.component.GfTextButton
import com.greenlabsfin.design.core.GfTheme

@Composable
fun TextButtonScreen() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GfTextButton(
            text = "활성화 XS 버튼",
            color = GfTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = GfTextButton.Style.XSmall,
        ) {}

        GfTextButton(
            text = "비활성화 XS 버튼",
            color = GfTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = GfTextButton.Style.XSmall,
            enabled = false
        ) {}

        GfTextButton(
            text = "활성화 SM 버튼",
            color = GfTheme.colorScheme.contents.neutralSecondary,
            rightIcon = Icons.Filled.KeyboardArrowRight,
            style = GfTextButton.Style.Small,
        ) {}

        GfTextButton(
            text = "비활성화 SM 버튼",
            color = GfTheme.colorScheme.contents.neutralSecondary,
            rightIcon = Icons.Filled.KeyboardArrowRight,
            style = GfTextButton.Style.Small,
            enabled = false
        ) {}

        GfTextButton(
            text = "활성화 MD 버튼",
            color = GfTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = GfTextButton.Style.Medium,
        ) {}

        GfTextButton(
            text = "비활성화 MD 버튼",
            color = GfTheme.colorScheme.contents.primary,
            rightIcon = Icons.Filled.ArrowDropDown,
            style = GfTextButton.Style.Medium,
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
