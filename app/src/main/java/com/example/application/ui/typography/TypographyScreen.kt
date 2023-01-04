package com.example.application.ui.typography

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.util.LocaleHelper
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.SeedButton
import com.greenlabsfin.design.component.SeedButtonDefaults
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.component.util.CatchScrollUp
import com.greenlabsfin.design.component.util.DecorateBackground
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun TypographyScreen(
    onScrollChange: (isScrollUp: Boolean) -> Unit,
) {
    val allowLocales = listOf(
        java.util.Locale.US,
        java.util.Locale.KOREA,
        java.util.Locale.JAPAN
    )
    val items = TypographyCategories.values().toList()
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val currentLocale = Locale.current
    val listState = rememberLazyListState()
    listState.CatchScrollUp { isScrollUp ->
        onScrollChange(isScrollUp)
    }

    DecorateBackground(SeedTheme.colorScheme.container.neutralTertiary) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            state = listState,
            content = {
                item {
                    SeedButton(
                        height = SeedButton.Height.Medium,
                        colors = SeedButtonDefaults.Colors.containerPrimary(),
                        text = currentLocale.language,
                        onClick = {
                            isExpanded = !isExpanded
                        }
                    )
                    DropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false }) {
                        allowLocales.forEach { locale ->
                            DropdownMenuItem(
                                onClick = {
                                    isExpanded = false
                                    LocaleHelper.setLocale(context, locale.language)
                                    (context as? Activity)?.recreate()
                                },
                                text = {
                                    SeedText(
                                        text = locale.language,
                                        style = SeedTheme.typoScheme.body.mediumRegular,
                                        color =
                                        if (locale.language == currentLocale.language) SeedTheme.colorScheme.contents.primary
                                        else SeedTheme.colorScheme.contents.neutralPrimary
                                    )
                                }
                            )
                        }
                    }
                }
                items(items) { item ->
                    TypographyCard(
                        resId = item.titleResId,
                        style = item.textStyle,
                    )
                }
            })
    }
}

enum class TypographyCategories(
    @StringRes val titleResId: Int,
) {
    HeadlineXLargeBold(
        titleResId = R.string.typography_title_headline_xlarge_bold
    ),
    HeadlineLargeBold(
        titleResId = R.string.typography_title_headline_large_bold
    ),
    HeadlineMediumBold(
        titleResId = R.string.typography_title_headline_medium_bold
    ),
    HeadlineSmallBold(
        titleResId = R.string.typography_title_headline_small_bold
    ),
    HeadlineSmallRegular(
        titleResId = R.string.typography_title_headline_small_regular
    ),
    BodyXLargeBold(
        titleResId = R.string.typography_title_body_xlarge_bold
    ),
    BodyXLargeRegular(
        titleResId = R.string.typography_title_body_xlarge_regular
    ),
    BodyLargeBold(
        titleResId = R.string.typography_title_body_large_bold
    ),
    BodyLargeRegular(
        titleResId = R.string.typography_title_body_large_regular
    ),
    BodyMediumBold(
        titleResId = R.string.typography_title_body_medium_bold
    ),
    BodyMediumMedium(
        titleResId = R.string.typography_title_body_medium_medium
    ),
    BodyMediumRegular(
        titleResId = R.string.typography_title_body_medium_regular
    ),
    BodySmallBold(
        titleResId = R.string.typography_title_body_small_bold
    ),
    BodySmallMedium(
        titleResId = R.string.typography_title_body_small_medium
    ),
    BodySmallRegular(
        titleResId = R.string.typography_title_body_small_regular
    ),
    CaptionXSmallRegular(
        titleResId = R.string.typography_title_caption_xsmall_regular
    ),
    CaptionXSmallBold(
        titleResId = R.string.typography_title_caption_xsmall_bold
    ),
    CaptionXSmallMedium(
        titleResId = R.string.typography_title_caption_xsmall_medium
    ),
    ;

    val textStyle: TextStyle
        @Composable
        get() = when (this) {
            HeadlineXLargeBold -> SeedTheme.typoScheme.headline.xLargeBold
            HeadlineLargeBold -> SeedTheme.typoScheme.headline.largeBold
            HeadlineMediumBold -> SeedTheme.typoScheme.headline.mediumBold
            HeadlineSmallBold -> SeedTheme.typoScheme.headline.smallBold
            HeadlineSmallRegular -> SeedTheme.typoScheme.headline.smallRegular
            BodyXLargeBold -> SeedTheme.typoScheme.body.xLargeBold
            BodyXLargeRegular -> SeedTheme.typoScheme.body.xLargeRegular
            BodyLargeBold -> SeedTheme.typoScheme.body.largeBold
            BodyLargeRegular -> SeedTheme.typoScheme.body.largeRegular
            BodyMediumBold -> SeedTheme.typoScheme.body.mediumBold
            BodyMediumMedium -> SeedTheme.typoScheme.body.mediumMedium
            BodyMediumRegular -> SeedTheme.typoScheme.body.mediumRegular
            BodySmallBold -> SeedTheme.typoScheme.body.smallBold
            BodySmallMedium -> SeedTheme.typoScheme.body.smallMedium
            BodySmallRegular -> SeedTheme.typoScheme.body.smallRegular
            CaptionXSmallRegular -> SeedTheme.typoScheme.caption.xSmallRegular
            CaptionXSmallBold -> SeedTheme.typoScheme.caption.xSmallBold
            CaptionXSmallMedium -> SeedTheme.typoScheme.caption.xSmallMedium
        }
}

@ThemedPreview
@Composable
fun TypographyScreenPreview() {
    SeedSampleTheme {
        TypographyScreen {}
    }
}
