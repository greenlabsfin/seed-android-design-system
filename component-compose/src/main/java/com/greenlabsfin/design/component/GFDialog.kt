package com.greenlabsfin.design.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.greenlabsfin.design.core.GfTheme

@Composable
fun GFDialog(
    modifier: Modifier = Modifier,
    itemVisible: Boolean,
    title: String? = null,
    message: String? = null,
    titleColor: Color = GfTheme.colorScheme.contents.neutralPrimary,
    messageColor: Color = GfTheme.colorScheme.contents.neutralSecondary,
    onDismissRequest: () -> Unit,
    backgroundContent: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (itemVisible) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(modifier = modifier.padding(
                    bottom = 20.dp
                )) {
                    Box(
                        modifier = modifier,
                        contentAlignment = Alignment.TopStart
                    ) {
                        backgroundContent?.let {
                            it()
                        }
                        Column(modifier = modifier.padding(
                            top = 24.dp,
                            start = 20.dp,
                            end = 20.dp
                        )) {
                            title?.let {
                                Text(
                                    text = it,
                                    style = GfTheme.typoScheme.headline.mediumBold,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    color = titleColor,
                                )
                            }
                            message?.let {
                                Text(
                                    text = it,
                                    style = GfTheme.typoScheme.body.mediumRegular,
                                    modifier = Modifier.padding(top = 8.dp),
                                    color = messageColor,
                                    maxLines = 4,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                    Surface(modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp)) {
                        content()
                    }
                }
            }
        }
    }
}