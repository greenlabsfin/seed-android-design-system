package co.seedglobal.design.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
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
import co.seedglobal.design.component.icons.Icons
import co.seedglobal.design.component.icons.bold.CheckLine
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.SeedColorScheme
import co.seedglobal.design.core.color.gray30
import co.seedglobal.design.core.color.gray40
import co.seedglobal.design.core.color.white

@Composable
fun SeedCheckbox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    enabled: Boolean = true,
    text: String? = null,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    colors: SeedCheckbox.Colors = SeedCheckboxDefaults.primary(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: (Boolean) -> Unit,
) {

    val backgroundAnimation =
        animateColorAsState(
            targetValue = colors.backgroundColor(
                enabled = enabled,
                selected = checked
            ).value
        )
    val iconAnimation =
        animateColorAsState(
            targetValue = colors.contentColor(
                enabled = enabled,
                selected = checked
            ).value
        )

    val paddingModifier = text?.let {
        Modifier.padding(end = 8.dp)
    } ?: Modifier

    val checkedBorderModifier =
        if (checked.not()) {
            Modifier.border(
                width = 1.dp,
                color = gray40,
                shape = RoundedCornerShape(checkBoxRadius)
            )
        } else {
            Modifier
        }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = modifier
                .then(
                    paddingModifier
                )
                .size(checkboxSize)
                .selectable(
                    selected = checked,
                    enabled = enabled,
                    role = Role.Checkbox,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = { onCheckedChange(checked.not()) })
                .background(
                    color = backgroundAnimation.value,
                    shape = RoundedCornerShape(checkBoxRadius)
                )
                .then(
                    checkedBorderModifier
                ),
            contentAlignment = Alignment.Center
        ) {
            SeedIcon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.OutlinedBold.CheckLine,
                contentDescription = "Check-Line Icon",
                tint = iconAnimation.value
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

private val checkBoxRadius = 6.dp
private val checkboxSize = 24.dp

object SeedCheckbox {
    @Stable
    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean, selected: Boolean): State<Color>

        @Composable
        fun contentColor(enabled: Boolean, selected: Boolean): State<Color>
    }
}

object SeedCheckboxDefaults {
    fun primary(
        checkedBackgroundColor: Color = SeedColorScheme().container.primary,
        uncheckedBackgroundColor: Color = white,
        checkedContentColor: Color = white,
        uncheckedContentColor: Color = gray30,
        disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
        disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
    ): SeedCheckbox.Colors =
        SeedCheckboxColors(
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
    ): SeedCheckbox.Colors =
        SeedCheckboxColors(
            checkedBackgroundColor = checkedBackgroundColor,
            uncheckedBackgroundColor = uncheckedBackgroundColor,
            checkedContentColor = checkedContentColor,
            uncheckedContentColor = uncheckedContentColor,
            disabledCheckedBackgroundColor = disabledCheckedBackgroundColor,
            disabledUncheckedContentColor = disabledUncheckedContentColor
        )
}

@Immutable
private data class SeedCheckboxColors(
    private val checkedBackgroundColor: Color = SeedColorScheme().container.primary,
    private val uncheckedBackgroundColor: Color = white,
    private val checkedContentColor: Color = white,
    private val uncheckedContentColor: Color = gray30,
    private val disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
    private val disabledUncheckedBackgroundColor: Color = uncheckedBackgroundColor.copy(0.3f),
    private val disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
) : SeedCheckbox.Colors {
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
