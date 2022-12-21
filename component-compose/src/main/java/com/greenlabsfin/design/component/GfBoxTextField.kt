package com.greenlabsfin.design.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
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
import com.greenlabsfin.design.core.GfTheme

object GfBoxTextField {
    enum class Style {
        Outline,
        Fill
    }
}

@Composable
internal fun GfBoxTextField(
    modifier: Modifier = Modifier,
    style: GfBoxTextField.Style,
    height: GFHeight,
    value: String,
    colors: GfTextFieldColors,
    label: String? = null,
    textStyle: TextStyle = GfTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = GfTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    suffixTextStyle: TextStyle? = null,
    onValueChange: (String) -> Unit = {},
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
) {
    val isFocused = interactionSource.collectIsFocusedAsState()
    val labelColor = colors.labelColor(
        enabled = enabled,
        error = isError,
        interactionSource = interactionSource,
    )
    val visibleClear = rememberUpdatedState(
        when {
            !showClear -> false
            readOnly -> false
            !enabled -> false
            isFocused.value && value.isNotEmpty() -> true
            else -> false
        }
    )
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    val outlineColor = colors.indicatorColor(
        enabled = enabled,
        isError = isError,
        readOnly = readOnly,
        interactionSource = interactionSource
    )
    val backgroundColor = colors.backgroundColor(enabled = enabled, readOnly = readOnly)
    val borderStroke = when (style) {
        GfBoxTextField.Style.Outline -> BorderStroke(width = 1.dp, color = outlineColor.value)
        GfBoxTextField.Style.Fill -> if (isError) BorderStroke(width = 1.dp,
            color = outlineColor.value) else null
    }

    Column(modifier = modifier) {
        label?.let {
            GfText(
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
                    minWidth = GfTextFieldDefaults.minWidth,
                    minHeight = GfTextFieldDefaults.minHeight,
                )
                .height(height.displayPixel),
            color = backgroundColor.value,
            border = borderStroke,
            shape = RoundedCornerShape(height.radius)
        ) {
            Row(
                modifier = modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                prefix?.let {
                    Decoration(
                        contentColor = colors.leadingIconColor(
                            enabled = enabled,
                            isError = isError
                        ).value,
                        content = prefix
                    )
                }


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
                        GfText(
                            text = placeholder,
                            style = mergedTextStyle,
                            color = colors.placeholderColor(enabled = enabled).value
                        )
                    }
                }
                if (visibleClear.value) {
                    Icon(
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
                        tint = GfTheme.colorScheme.contents.neutralTertiary,
                    )
                }
                suffix?.let {
                    Decoration(
                        contentColor = colors.trailingIconColor(
                            enabled = enabled,
                            isError = isError
                        ).value,
                        typography = suffixTextStyle,
                        content = suffix
                    )
                }
            }
        }

        errorText?.let {
            if (isError) {
                Spacer(modifier = Modifier.height(4.dp))
                GfText(
                    text = errorText,
                    style = GfTheme.typoScheme.body.smallRegular,
                    color = GfTheme.colorScheme.contents.error,
                )
            }
        }
    }
}
