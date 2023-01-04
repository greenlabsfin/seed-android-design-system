package com.greenlabsfin.design.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.greenlabsfin.design.core.SeedTheme

@Composable
fun SeedOutlineTextField(
    modifier: Modifier = Modifier,
    height: SeedTextField.Height,
    value: String,
    label: String? = null,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = SeedTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    suffixTextStyle: TextStyle? = SeedTheme.typoScheme.body.smallRegular.merge(TextStyle(color = SeedTheme.colorScheme.contents.neutralTertiary)),
    colors: SeedTextFieldColors = SeedTextFieldDefaults.outlineTextFieldColors(),
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
    SeedBoxTextField(
        modifier = modifier,
        style = SeedTextField.Style.Outline,
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
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        errorText = errorText,
        singleLine = singleLine,
        showClear = showClear,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        interactionSource = interactionSource,
        onValueChange = onValueChange,
    )
}
