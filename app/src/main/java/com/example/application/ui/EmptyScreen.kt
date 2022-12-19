package com.example.application.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.application.ui.theme.GFSampleTheme
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.typo.GfTypoScheme

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    typoScheme: GfTypoScheme = GfTheme.typoScheme,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "EmptyScreen",
            style = typoScheme.headline.xLargeBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    GFSampleTheme {
        EmptyScreen()
    }
}