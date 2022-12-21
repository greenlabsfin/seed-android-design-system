package com.greenlabsfin.design.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.greenlabsfin.design.core.GfTheme

@Composable
fun GfFillTextField(
    modifier: Modifier = Modifier,
    height: GFHeight,
    value: String,
    label: String? = null,
    textStyle: TextStyle = GfTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = GfTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    suffixTextStyle: TextStyle? = GfTheme.typoScheme.body.smallRegular.merge(TextStyle(color = GfTheme.colorScheme.contents.neutralTertiary)),
    onValueChange: (String) -> Unit = {},
    colors: GfTextFieldColors = GfTextFieldDefaults.fillTextFieldColors(),
    enabled: Boolean = true,
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
    GfBoxTextField(
        modifier = modifier,
        style = GfBoxTextField.Style.Fill,
        height = height,
        value = value,
        colors = colors,
        label = label,
        textStyle = textStyle,
        labelTextStyle = labelTextStyle,
        placeholder = placeholder,
        prefix = prefix,
        suffix = suffix,
        suffixTextStyle = suffixTextStyle,
        onValueChange = onValueChange,
        enabled = enabled,
        isError = isError,
        errorText = errorText,
        singleLine = singleLine,
        showClear = showClear,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        interactionSource = interactionSource
    )
}
