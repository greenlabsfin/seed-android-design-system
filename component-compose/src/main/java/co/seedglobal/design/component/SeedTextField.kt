package co.seedglobal.design.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.icons.Icons
import co.seedglobal.design.component.icons.filled.Delete
import co.seedglobal.design.core.LocalSeedContentColor
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.typo.ProvideSeedTextStyle
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@Composable
fun SeedTextField(
    value: String,
    style: SeedTextField.Style,
    modifier: Modifier = Modifier,
    height: SeedTextField.Size? = null,
    label: String? = null,
    labelSpace: Dp = 2.dp,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = SeedTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    suffixTextStyle: TextStyle? = SeedTheme.typoScheme.body.smallRegular.merge(TextStyle(color = SeedTheme.colorScheme.contents.neutralTertiary)),
    colors: SeedTextFieldColors = SeedTextFieldDefaults.defaultColorsByStyle(style),
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
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    onValueChange: (String) -> Unit,
) {
    when (style) {
        SeedTextField.Style.Line -> SeedLineTextField(
            modifier = modifier,
            value = value,
            colors = colors,
            label = label,
            labelSpace = labelSpace,
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
            onKeyEvent = onKeyEvent,
            onValueChange = onValueChange,
        )
        else -> SeedBoxTextField(
            modifier = modifier,
            style = style,
            height = height ?: throw NullPointerException("height must require"),
            value = value,
            colors = colors,
            label = label,
            labelSpace = labelSpace,
            textStyle = textStyle,
            labelTextStyle = labelTextStyle,
            placeholder = placeholder,
            prefix = prefix,
            suffix = suffix,
            suffixTextStyle = suffixTextStyle,
            enabled = enabled,
            isError = isError,
            errorText = errorText,
            singleLine = singleLine,
            showClear = showClear,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            interactionSource = interactionSource,
            onKeyEvent = onKeyEvent,
            onValueChange = onValueChange,
        )
    }
}

@Composable
private fun SeedLineTextField(
    value: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    labelSpace: Dp = 2.dp,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = SeedTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable () -> Unit = {},
    suffix: @Composable () -> Unit = {},
    suffixTextStyle: TextStyle? = SeedTheme.typoScheme.body.smallRegular.merge(TextStyle(color = SeedTheme.colorScheme.contents.neutralTertiary)),
    colors: SeedTextFieldColors = SeedTextFieldDefaults.lineTextFieldColors(),
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
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    onValueChange: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val isFocused = interactionSource.collectIsFocusedAsState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
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
    Column(modifier = modifier.bringIntoViewRequester(bringIntoViewRequester)) {
        label?.let {
            SeedText(
                text = label,
                modifier = modifier,
                color = labelColor.value,
                style = labelTextStyle,
            )
            Spacer(modifier = Modifier.height(labelSpace))
        }

        SeedSurface(
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
                    content = prefix
                )

                Box(modifier = modifier.weight(1f, false)) {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusEvent {
                                if (it.isFocused) {
                                    scope.launch {
                                        awaitFrame()
                                        bringIntoViewRequester.bringIntoView()
                                    }
                                }
                            }
                            .onKeyEvent { onKeyEvent(it) },
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
                        tint = SeedTheme.colorScheme.contents.neutralTertiary,
                    )
                }
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

        Spacer(modifier = Modifier.height(8.dp))

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

object SeedTextField {
    enum class Style {
        Outline,
        Fill,
        Line,
    }

    enum class Size(val value: Dp) {
        XLarge(64.dp),
        Large(56.dp),
        Medium(48.dp),
        Small(40.dp),
        XSmall(32.dp);

        val typography: TextStyle
            @Composable
            get() = when (this) {
                XLarge -> SeedTheme.typoScheme.body.xLargeBold
                Large -> SeedTheme.typoScheme.body.largeBold
                Medium -> SeedTheme.typoScheme.body.mediumMedium
                else -> SeedTheme.typoScheme.body.smallMedium
            }

        val radius: Dp
            get() = when (this) {
                XLarge, Large -> 12.dp
                Medium -> 10.dp
                Small, XSmall -> 8.dp
            }
    }
}

@Immutable
object SeedTextFieldDefaults {
    val minWidth = 24.dp
    val minHeight = 32.dp

    @Composable
    fun defaultColorsByStyle(style: SeedTextField.Style): SeedTextFieldColors = when (style) {
        SeedTextField.Style.Outline -> outlineTextFieldColors()
        SeedTextField.Style.Fill -> fillTextFieldColors()
        SeedTextField.Style.Line -> lineTextFieldColors()
    }

    @Composable
    fun fillTextFieldColors(
        textColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        backgroundColor: Color = SeedTheme.colorScheme.container.neutralSecondary,
        disabledBackgroundColor: Color = SeedTheme.colorScheme.container.neutralTertiary,
        cursorColor: Color = SeedTheme.colorScheme.contents.primary,
        errorCursorColor: Color = SeedTheme.colorScheme.contents.error,
        errorOutlineColor: Color = SeedTheme.colorScheme.contents.error,
        prefixIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledPrefixIconColor: Color = prefixIconColor,
        errorPrefixIconColor: Color = prefixIconColor,
        suffixIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledSuffixIconColor: Color = suffixIconColor,
        errorSuffixIconColor: Color = suffixIconColor,
        labelColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        unfocusedLabelColor: Color = labelColor,
        disabledLabelColor: Color = labelColor,
        errorLabelColor: Color = labelColor,
        placeholderColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledPlaceholderColor: Color = placeholderColor,
    ): SeedTextFieldColors =
        SeedDefaultTextFieldColors(
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            focusedIndicatorColor = SeedTheme.colorScheme.container.inverse,
            unfocusedIndicatorColor = SeedTheme.colorScheme.container.outline,
            errorIndicatorColor = errorOutlineColor,
            disabledIndicatorColor = SeedTheme.colorScheme.container.outline,
            leadingIconColor = prefixIconColor,
            disabledLeadingIconColor = disabledPrefixIconColor,
            errorLeadingIconColor = errorPrefixIconColor,
            trailingIconColor = suffixIconColor,
            disabledTrailingIconColor = disabledSuffixIconColor,
            errorTrailingIconColor = errorSuffixIconColor,
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,
            placeholderColor = placeholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            readOnlyBackgroundColor = SeedTheme.colorScheme.container.neutralTertiary,
            readOnlyIndicatorColor = SeedTheme.colorScheme.container.outline,
        )

    @Composable
    fun outlineTextFieldColors(
        textColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        backgroundColor: Color = SeedTheme.colorScheme.container.background,
        readOnlyBackgroundColor: Color = SeedTheme.colorScheme.container.neutralTertiary,
        disabledBackgroundColor: Color = SeedTheme.colorScheme.container.neutralTertiary,
        cursorColor: Color = SeedTheme.colorScheme.contents.primary,
        errorCursorColor: Color = cursorColor,
        focusedOutlineColor: Color = SeedTheme.colorScheme.container.inverse,
        unfocusedOutlineColor: Color = SeedTheme.colorScheme.container.outline,
        readOnlyOutlineColor: Color = SeedTheme.colorScheme.container.outline,
        disabledOutlineColor: Color = SeedTheme.colorScheme.container.outline,
        errorOutlineColor: Color = SeedTheme.colorScheme.contents.error,
        prefixIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledPrefixIconColor: Color = prefixIconColor,
        errorPrefixIconColor: Color = prefixIconColor,
        suffixIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledSuffixIconColor: Color = suffixIconColor,
        errorSuffixIconColor: Color = suffixIconColor,
        focusedLabelColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        unfocusedLabelColor: Color = focusedLabelColor,
        disabledLabelColor: Color = focusedLabelColor,
        errorLabelColor: Color = focusedLabelColor,
        placeholderColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledPlaceholderColor: Color = placeholderColor,
    ): SeedTextFieldColors =
        SeedDefaultTextFieldColors(
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            focusedIndicatorColor = focusedOutlineColor,
            unfocusedIndicatorColor = unfocusedOutlineColor,
            errorIndicatorColor = errorOutlineColor,
            disabledIndicatorColor = disabledOutlineColor,
            leadingIconColor = prefixIconColor,
            disabledLeadingIconColor = disabledPrefixIconColor,
            errorLeadingIconColor = errorPrefixIconColor,
            trailingIconColor = suffixIconColor,
            disabledTrailingIconColor = disabledSuffixIconColor,
            errorTrailingIconColor = errorSuffixIconColor,
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,
            placeholderColor = placeholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            readOnlyBackgroundColor = readOnlyBackgroundColor,
            readOnlyIndicatorColor = readOnlyOutlineColor,
        )

    @Composable
    fun lineTextFieldColors(
        textColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        disabledTextColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        cursorColor: Color = SeedTheme.colorScheme.contents.primary,
        errorCursorColor: Color = SeedTheme.colorScheme.contents.error,
        focusedLineColor: Color = SeedTheme.colorScheme.container.primary,
        unfocusedLineColor: Color = SeedTheme.colorScheme.container.outline,
        disabledLineColor: Color = SeedTheme.colorScheme.container.outline,
        errorLineColor: Color = SeedTheme.colorScheme.contents.error,
        prefixIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledPrefixIconColor: Color = prefixIconColor,
        errorPrefixIconColor: Color = prefixIconColor,
        suffixIconColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledSuffixIconColor: Color = suffixIconColor,
        errorSuffixIconColor: Color = suffixIconColor,
        focusedLabelColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        unfocusedLabelColor: Color = focusedLabelColor,
        disabledLabelColor: Color = focusedLabelColor,
        errorLabelColor: Color = focusedLabelColor,
        placeholderColor: Color = SeedTheme.colorScheme.contents.neutralTertiary,
        disabledPlaceholderColor: Color = placeholderColor,
    ): SeedTextFieldColors =
        SeedDefaultTextFieldColors(
            textColor = textColor,
            disabledTextColor = disabledTextColor,
            cursorColor = cursorColor,
            errorCursorColor = errorCursorColor,
            focusedIndicatorColor = focusedLineColor,
            unfocusedIndicatorColor = unfocusedLineColor,
            errorIndicatorColor = errorLineColor,
            disabledIndicatorColor = disabledLineColor,
            leadingIconColor = prefixIconColor,
            disabledLeadingIconColor = disabledPrefixIconColor,
            errorLeadingIconColor = errorPrefixIconColor,
            trailingIconColor = suffixIconColor,
            disabledTrailingIconColor = disabledSuffixIconColor,
            errorTrailingIconColor = errorSuffixIconColor,
            backgroundColor = SeedTheme.colorScheme.container.background,
            disabledBackgroundColor = SeedTheme.colorScheme.container.background,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            disabledLabelColor = disabledLabelColor,
            errorLabelColor = errorLabelColor,
            placeholderColor = placeholderColor,
            disabledPlaceholderColor = disabledPlaceholderColor,
            readOnlyBackgroundColor = SeedTheme.colorScheme.container.neutralTertiary,
            readOnlyIndicatorColor = SeedTheme.colorScheme.container.outline,
        )
}

@Stable
interface SeedTextFieldColors : TextFieldColors {
    @Composable
    fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        readOnly: Boolean,
        interactionSource: InteractionSource,
    ): State<Color>

    @Composable
    fun backgroundColor(enabled: Boolean, readOnly: Boolean): State<Color>
}

@Immutable
private data class SeedDefaultTextFieldColors(
    private val textColor: Color,
    private val disabledTextColor: Color,
    private val cursorColor: Color,
    private val errorCursorColor: Color,
    private val focusedIndicatorColor: Color,
    private val unfocusedIndicatorColor: Color,
    private val errorIndicatorColor: Color,
    private val disabledIndicatorColor: Color,
    private val readOnlyIndicatorColor: Color,
    private val leadingIconColor: Color,
    private val disabledLeadingIconColor: Color,
    private val errorLeadingIconColor: Color,
    private val trailingIconColor: Color,
    private val disabledTrailingIconColor: Color,
    private val errorTrailingIconColor: Color,
    private val backgroundColor: Color,
    private val disabledBackgroundColor: Color,
    private val readOnlyBackgroundColor: Color,
    private val focusedLabelColor: Color,
    private val unfocusedLabelColor: Color,
    private val disabledLabelColor: Color,
    private val errorLabelColor: Color,
    private val placeholderColor: Color,
    private val disabledPlaceholderColor: Color,
) : SeedTextFieldColors {
    @Composable
    override fun leadingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                enabled.not() -> disabledLeadingIconColor
                isError -> errorLeadingIconColor
                else -> leadingIconColor
            }
        )
    }

    @Composable
    override fun trailingIconColor(enabled: Boolean, isError: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                enabled.not() -> disabledTrailingIconColor
                isError -> errorTrailingIconColor
                else -> trailingIconColor
            }
        )
    }

    @Composable
    override fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            enabled.not() -> disabledIndicatorColor
            isError -> errorIndicatorColor
            focused -> focusedIndicatorColor
            else -> unfocusedIndicatorColor
        }
        return if (enabled) {
            animateColorAsState(targetValue, tween(durationMillis = 150))
        } else {
            rememberUpdatedState(targetValue)
        }
    }

    @Composable
    override fun indicatorColor(
        enabled: Boolean,
        isError: Boolean,
        readOnly: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            enabled.not() -> disabledIndicatorColor
            isError -> errorIndicatorColor
            readOnly -> readOnlyIndicatorColor
            focused -> focusedIndicatorColor
            else -> unfocusedIndicatorColor
        }
        return if (enabled) {
            animateColorAsState(targetValue, tween(durationMillis = 150))
        } else {
            rememberUpdatedState(targetValue)
        }
    }

    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun backgroundColor(enabled: Boolean, readOnly: Boolean): State<Color> {
        return rememberUpdatedState(
            when {
                enabled.not() -> disabledBackgroundColor
                readOnly -> readOnlyBackgroundColor
                else -> backgroundColor
            }
        )
    }

    @Composable
    override fun placeholderColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) placeholderColor else disabledPlaceholderColor)
    }

    @Composable
    override fun labelColor(
        enabled: Boolean,
        error: Boolean,
        interactionSource: InteractionSource,
    ): State<Color> {
        val focused by interactionSource.collectIsFocusedAsState()

        val targetValue = when {
            enabled.not() -> disabledLabelColor
            error -> errorLabelColor
            focused -> focusedLabelColor
            else -> unfocusedLabelColor
        }
        return rememberUpdatedState(targetValue)
    }

    @Composable
    override fun textColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) textColor else disabledTextColor)
    }

    @Composable
    override fun cursorColor(isError: Boolean): State<Color> {
        return rememberUpdatedState(if (isError) errorCursorColor else cursorColor)
    }
}


@Composable
internal fun Decoration(
    contentColor: Color,
    typography: TextStyle? = null,
    content: @Composable () -> Unit,
) {
    val colorAndEmphasis: @Composable () -> Unit = @Composable {
        CompositionLocalProvider(LocalSeedContentColor provides contentColor, content = content)
    }

    if (typography == null) {
        colorAndEmphasis()
    } else {
        ProvideSeedTextStyle(typography, colorAndEmphasis)
    }
}

@Composable
private fun SeedBoxTextField(
    modifier: Modifier = Modifier,
    style: SeedTextField.Style,
    height: SeedTextField.Size,
    value: String,
    colors: SeedTextFieldColors,
    label: String? = null,
    labelSpace: Dp = 2.dp,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    labelTextStyle: TextStyle = SeedTheme.typoScheme.body.mediumBold,
    placeholder: String = "",
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    suffixTextStyle: TextStyle? = null,
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
    onKeyEvent: (KeyEvent) -> Boolean = { false },
    onValueChange: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val isFocused = interactionSource.collectIsFocusedAsState()
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val labelColor = colors.labelColor(
        enabled = enabled,
        error = isError,
        interactionSource = interactionSource,
    )
    val visibleClear = rememberUpdatedState(
        // 순서 바꾸지 말것!!
        when {
            showClear.not() -> false
            readOnly -> false
            enabled.not() -> false
            isFocused.value && value.isNotEmpty() -> true
            else -> false
        }
    )
    val textColor = textStyle.color.takeOrElse { colors.textColor(enabled).value }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))
    val outlineColor = colors.indicatorColor(
        enabled = enabled,
        isError = isError,
        readOnly = readOnly,
        interactionSource = interactionSource
    )
    val backgroundColor = colors.backgroundColor(enabled = enabled, readOnly = readOnly)
    val borderStroke = when (style) {
        SeedTextField.Style.Outline -> BorderStroke(width = 1.dp, color = outlineColor.value)
        SeedTextField.Style.Fill ->
            if (isError) BorderStroke(width = 1.dp, color = outlineColor.value)
            else null
        else -> throw IllegalArgumentException("unsupported style:${style.name}")
    }

    Column(modifier = modifier.bringIntoViewRequester(bringIntoViewRequester)) {
        label?.let {
            SeedText(
                text = label,
                modifier = modifier,
                color = labelColor.value,
                style = labelTextStyle,
            )
            Spacer(modifier = Modifier.height(labelSpace))
        }

        SeedSurface(
            modifier = modifier
                .defaultMinSize(
                    minWidth = SeedTextFieldDefaults.minWidth,
                    minHeight = SeedTextFieldDefaults.minHeight,
                )
                .height(height.value),
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusEvent {
                                if (it.isFocused) {
                                    scope.launch {
                                        awaitFrame()
                                        bringIntoViewRequester.bringIntoView()
                                    }
                                }
                            }
                            .onKeyEvent { onKeyEvent(it) },
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
                        tint = SeedTheme.colorScheme.contents.neutralTertiary,
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
                SeedText(
                    text = errorText,
                    style = SeedTheme.typoScheme.body.smallRegular,
                    color = SeedTheme.colorScheme.contents.error,
                )
            }
        }
    }
}
