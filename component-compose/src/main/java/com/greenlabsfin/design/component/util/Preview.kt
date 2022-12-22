package com.greenlabsfin.design.component.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Dark Theme",
    uiMode = UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Light Theme"
)
annotation class GfPreview(
    val name: String = "",
)