package com.greenlabsfin.design.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.greenlabsfin.design.core.SeedTheme
import com.greenlabsfin.design.core.color.SeedColorScheme
import com.greenlabsfin.design.core.color.gray30
import com.greenlabsfin.design.core.color.gray40
import com.greenlabsfin.design.core.color.white

@Composable
fun SeedRadioButton(
    selected: Boolean,
    enabled: Boolean = true,
    text: String? = null,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    colors: SeedRadioButton.Colors = SeedRadioDefaults.primary(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {

    val backgroundAnimation =
        animateColorAsState(
            targetValue = colors.backgroundColor(
                enabled = enabled,
                selected = selected).value
        )
    val dotAnimation =
        animateColorAsState(
            targetValue = colors.contentColor(
                enabled = enabled,
                selected = selected).value
        )

    val paddingModifier = text?.let {
        Modifier.padding(end = 8.dp)
    } ?: Modifier

    val selectedBorderModifier =
        if (selected.not()) {
            Modifier.border(width = 1.dp,
                color = gray40,
                shape = CircleShape)
        } else Modifier

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .then(
                    paddingModifier
                )
                .size(radioBackgroundSize)
                .selectable(
                    selected = selected,
                    enabled = enabled,
                    role = Role.RadioButton,
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        bounded = false,
                        radius = 24.dp
                    ),
                    onClick = onClick
                )
                .background(
                    color = backgroundAnimation.value,
                    shape = CircleShape
                )
                .then(
                    selectedBorderModifier
                ),
            contentAlignment = Alignment.Center,
        ) {
            Spacer(
                modifier = Modifier
                    .size(radioDotSize)
                    .background(
                        color = dotAnimation.value,
                        shape = CircleShape
                    )
            )
        }

        text?.let {
            SeedText(
                text = it,
                style = textStyle
            )
        }
    }
}

private val radioBackgroundSize = 24.dp
private val radioDotSize = 8.dp

object SeedRadioButton {
    @Stable
    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean, selected: Boolean): State<Color>

        @Composable
        fun contentColor(enabled: Boolean, selected: Boolean): State<Color>
    }
}

object SeedRadioDefaults {
    fun primary(
        checkedBackgroundColor: Color = SeedColorScheme().container.primary,
        uncheckedBackgroundColor: Color = white,
        checkedContentColor: Color = white,
        uncheckedContentColor: Color = gray30,
        disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
        disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
    ): SeedRadioButton.Colors =
        SeedRadioColors(
            checkedBackgroundColor = checkedBackgroundColor,
            uncheckedBackgroundColor = uncheckedBackgroundColor,
            checkedContentColor = checkedContentColor,
            uncheckedContentColor = uncheckedContentColor,
            disabledCheckedBackgroundColor = disabledCheckedBackgroundColor,
            disabledUncheckedContentColor = disabledUncheckedContentColor
        )

    fun custom(
        checkedBackgroundColor: Color = SeedColorScheme().container.primary,
        uncheckedBackgroundColor: Color = white,
        checkedContentColor: Color = white,
        uncheckedContentColor: Color = gray30,
        disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
        disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
    ): SeedRadioButton.Colors =
        SeedRadioColors(
            checkedBackgroundColor = checkedBackgroundColor,
            uncheckedBackgroundColor = uncheckedBackgroundColor,
            checkedContentColor = checkedContentColor,
            uncheckedContentColor = uncheckedContentColor,
            disabledCheckedBackgroundColor = disabledCheckedBackgroundColor,
            disabledUncheckedContentColor = disabledUncheckedContentColor
        )
}

@Immutable
private data class SeedRadioColors(
    private val checkedBackgroundColor: Color = SeedColorScheme().container.primary,
    private val uncheckedBackgroundColor: Color = white,
    private val checkedContentColor: Color = white,
    private val uncheckedContentColor: Color = gray30,
    private val disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
    private val disabledUncheckedBackgroundColor: Color = uncheckedBackgroundColor.copy(0.3f),
    private val disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
) : SeedRadioButton.Colors {
    @Composable
    override fun backgroundColor(enabled: Boolean, selected: Boolean): State<Color> =
        rememberUpdatedState(
            if (enabled) {
                if (selected) checkedBackgroundColor else uncheckedBackgroundColor
            } else {
                if (selected) disabledCheckedBackgroundColor else disabledUncheckedBackgroundColor
            }
        )

    @Composable
    override fun contentColor(enabled: Boolean, selected: Boolean): State<Color> =
        rememberUpdatedState(
            if (enabled) {
                if (selected) checkedContentColor else uncheckedContentColor
            } else {
                if (selected) checkedContentColor else disabledUncheckedContentColor
            }
        )
}
