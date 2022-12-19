package com.example.application.ui.typography

import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.util.LocaleHelper
import com.greenlabsfin.design.core.GfTheme

@Composable
fun TypographyScreen() {
    val allowLocales = listOf(
        java.util.Locale.US,
        java.util.Locale.KOREA,
        java.util.Locale.JAPAN
    )
    val items = TypographyCategories.values().toList()
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val currentLocale = Locale.current
    Column {
        Column {
            Card(
                content = {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 20.dp)
                            .clickable { isExpanded = !isExpanded },
                        text = currentLocale.language,
                        style = GfTheme.typoScheme.body.mediumRegular,
                    )
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
                            Text(
                                text = locale.language,
                                style = GfTheme.typoScheme.body.mediumRegular,
                                color =
                                if (locale.language == currentLocale.language) GfTheme.colorScheme.contents.primary
                                else GfTheme.colorScheme.contents.neutralPrimary
                            )
                        }
                    )
                }
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                TypographyCard(
                    resId = item.titleResId,
                    style = item.textStyle,
                )
            }
        }
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
            HeadlineXLargeBold -> GfTheme.typoScheme.headline.xLargeBold
            HeadlineLargeBold -> GfTheme.typoScheme.headline.largeBold
            HeadlineMediumBold -> GfTheme.typoScheme.headline.mediumBold
            HeadlineSmallBold -> GfTheme.typoScheme.headline.smallBold
            HeadlineSmallRegular -> GfTheme.typoScheme.headline.smallRegular
            BodyXLargeBold -> GfTheme.typoScheme.body.xLargeBold
            BodyXLargeRegular -> GfTheme.typoScheme.body.xLargeRegular
            BodyLargeBold -> GfTheme.typoScheme.body.largeBold
            BodyLargeRegular -> GfTheme.typoScheme.body.largeRegular
            BodyMediumBold -> GfTheme.typoScheme.body.mediumBold
            BodyMediumMedium -> GfTheme.typoScheme.body.mediumMedium
            BodyMediumRegular -> GfTheme.typoScheme.body.mediumRegular
            BodySmallBold -> GfTheme.typoScheme.body.smallBold
            BodySmallMedium -> GfTheme.typoScheme.body.smallMedium
            BodySmallRegular -> GfTheme.typoScheme.body.smallRegular
            CaptionXSmallRegular -> GfTheme.typoScheme.caption.xSmallRegular
            CaptionXSmallBold -> GfTheme.typoScheme.caption.xSmallBold
            CaptionXSmallMedium -> GfTheme.typoScheme.caption.xSmallMedium
        }
}

@Preview
@Composable
fun TypographyScreenPreview() {
    TypographyScreen()
}