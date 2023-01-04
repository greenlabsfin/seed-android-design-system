package com.example.application.ui.typography

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.application.R
import com.example.application.ui.theme.SeedSampleTheme
import com.example.application.util.ThemedPreview
import com.greenlabsfin.design.component.SeedText
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun TypographyCard(
    @StringRes resId: Int,
    style: TextStyle = SeedTheme.typoScheme.body.mediumMedium,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SeedText(
            text = stringResource(id = resId),
            style = style
        )

        Row {
            ValuePanel(
                title = "Size",
                value = style.fontSize.toString()
            )
            Spacer(modifier = Modifier.width(8.dp))
            ValuePanel(
                title = "Line Height",
                value = style.lineHeight.percentOf(style.fontSize)
            )
            Spacer(modifier = Modifier.width(8.dp))
            ValuePanel(
                title = "Letter Spacing",
                value = style.letterSpacing.percentOf(style.fontSize)
            )
            Spacer(modifier = Modifier.width(8.dp))
            ValuePanel(
                title = "Weight",
                value = style.fontWeight?.weight?.toString() ?: ""
            )
        }
    }
}

private fun TextUnit.percentOf(other: TextUnit): String {
    return "${this.value.div(other.value).times(100)}%"
}

@Composable
fun ValuePanel(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
) {
    Column(
        modifier = modifier
    ) {
        SeedText(
            text = title,
            style = SeedTheme.typoScheme.body.smallBold
        )

        SeedText(
            text = value,
            style = SeedTheme.typoScheme.body.smallRegular
        )
    }
}


@ThemedPreview
@Composable
fun TypographyCardPreview() {
    SeedSampleTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            TypographyCard(resId = R.string.typography_title_body_medium_medium)
        }
    }
}
