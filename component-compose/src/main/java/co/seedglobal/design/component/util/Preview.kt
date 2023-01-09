package co.seedglobal.design.component.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview


@Preview(
    name = "Dark Theme",
    uiMode = UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Light Theme"
)
annotation class SeedPreview(
    val name: String = "",
)
