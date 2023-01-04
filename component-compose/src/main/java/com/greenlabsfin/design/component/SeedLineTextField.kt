package com.greenlabsfin.design.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.component.icons.Icons
import com.greenlabsfin.design.component.icons.filled.Delete
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun SeedLineTextField(
    value: String,
    modifier: Modifier = Modifier,
    colors: SeedTextFieldColors = SeedTextFieldDefaults.lineTextFieldColors(),
    label: String? = null,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = SeedTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    errorText: String? = null,
    singleLine: Boolean = true,
    showClear: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onValueChange: (String) -> Unit,
) {
    val isFocused = interactionSource.collectIsFocusedAsState()
    val labelColor = colors.labelColor(
        enabled = enabled,
        error = isError,
        interactionSource = interactionSource,
    )
    val visibleClear = rememberUpdatedState(
        when {
            showClear.not() -> false
            readOnly -> false
            enabled.not() -> false
            isFocused.value && value.isNotEmpty() -> true
            else -> false
        }
    )
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    val indicatorColor = colors.indicatorColor(
        enabled = enabled,
        isError = isError,
        readOnly = readOnly,
        interactionSource = interactionSource
    )

    val backgroundColor = colors.backgroundColor(enabled = enabled, readOnly = readOnly)
    Column(modifier = modifier) {
        label?.let {
            SeedText(
                text = label,
                modifier = modifier,
                color = labelColor.value,
                style = labelTextStyle,
            )
            Spacer(modifier = Modifier.height(2.dp))
        }

        Surface(
            modifier = modifier
                .defaultMinSize(
                    minWidth = SeedTextFieldDefaults.minWidth,
                    minHeight = SeedTextFieldDefaults.minHeight,
                )
                .wrapContentHeight(),
            color = backgroundColor.value,
        ) {
            Row(
                modifier = modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Decoration(
                    contentColor = colors.leadingIconColor(
                        enabled = enabled,
                        isError = isError
                    ).value,
                    content = prefix)

                Box(modifier = modifier.weight(1f, false)) {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = enabled,
                        readOnly = readOnly,
                        textStyle = mergedTextStyle,
                        visualTransformation = visualTransformation,
                        keyboardOptions = keyboardOptions,
                        keyboardActions = keyboardActions,
                        maxLines = maxLines,
                        singleLine = singleLine,
                        interactionSource = interactionSource,
                        cursorBrush = SolidColor(colors.cursorColor(isError).value)
                    )
                    if (value.isBlank()) {
                        SeedText(
                            text = placeholder,
                            style = mergedTextStyle,
                            color = colors.placeholderColor(enabled = enabled).value
                        )
                    }
                }
                if (visibleClear.value) {
                    SeedIcon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                onValueChange("")
                            },
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "clear",
                        tint = SeedTheme.colorScheme.contents.neutralTertiary,
                    )
                }
                Decoration(
                    contentColor = colors.trailingIconColor(
                        enabled = enabled,
                        isError = isError
                    ).value,
                    content = suffix
                )
            }
        }

        Divider(
            color = indicatorColor.value,
            thickness = 2.dp
        )

        errorText?.let {
            if (isError) {
                Spacer(modifier = Modifier.height(4.dp))
                SeedText(
                    text = errorText,
                    style = SeedTheme.typoScheme.body.smallRegular,
                    color = SeedTheme.colorScheme.contents.error,
                )
            }
        }
    }
}
