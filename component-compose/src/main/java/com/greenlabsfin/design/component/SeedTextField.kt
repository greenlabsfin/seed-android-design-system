@file:JvmName("SeedTextFieldKt")

package com.greenlabsfin.design.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.LocalSeedContainerColor
import com.greenlabsfin.design.core.typo.ProvideSeedTextStyle

@Immutable
object SeedTextFieldDefaults {

    val minWidth = 280.dp
    val minHeight = 32.dp

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
        CompositionLocalProvider(LocalSeedContainerColor provides contentColor, content = content)
    }

    typography?.let { ProvideSeedTextStyle(it, colorAndEmphasis) } ?: colorAndEmphasis()
}


