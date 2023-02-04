package co.seedglobal.design.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import co.seedglobal.design.component.util.SeedPreview
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.SeedColorScheme
import co.seedglobal.design.core.color.gray30
import co.seedglobal.design.core.color.white

enum class SwitchSize {
    Large,
    Small
}

@Composable
fun SeedSwitch(
    checked: Boolean,
    enabled: Boolean = true,
    text: String? = null,
    textStyle: TextStyle = SeedTheme.typoScheme.body.mediumRegular,
    switchSize: SwitchSize = SwitchSize.Large,
    colors: SeedSwitch.Colors = SeedSwitchDefaults.primary(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCheckedChange: (Boolean) -> Unit,
) {

    val switchWidth = when (switchSize) {
        SwitchSize.Large -> 54.dp
        SwitchSize.Small -> 40.dp
    }

    val switchHeight = when (switchSize) {
        SwitchSize.Large -> 32.dp
        SwitchSize.Small -> 24.dp
    }

    val thumbRadius = (switchHeight / 2) - 3.dp

    val trackColor = colors.backgroundColor(enabled = enabled, selected = checked).value
    val thumbColor = colors.contentColor(enabled = true, selected = true).value


    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (checked)
            with(LocalDensity.current) { (switchWidth - thumbRadius - 3.dp).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + 3.dp).toPx() }
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(
            modifier = Modifier
                .size(width = switchWidth, height = switchHeight)
                .selectable(
                    selected = checked,
                    enabled = enabled,
                    role = Role.Switch,
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onCheckedChange(checked)
                    }
                )
        ) {
            // Track
            drawRoundRect(
                color = trackColor,
                cornerRadius = CornerRadius(30.dp.toPx()),
            )

            // Thumb
            drawCircle(
                color = thumbColor,
                radius = thumbRadius.toPx(),
                center = Offset(
                    x = animatePosition.value,
                    y = size.height / 2
                )
            )
        }
        text?.let {
            SeedText(
                modifier = Modifier.padding(start = 8.dp),
                text = it,
                style = textStyle
            )
        }
    }
}

@SeedPreview("SwitchPreview")
@Composable
fun SwitchPreview() {
    var checked by remember {
        mutableStateOf(true)
    }
    SeedTheme(
        colorScheme = SeedColorScheme.default(isSystemInDarkTheme())
    ) {
        SeedSurface(
            color = SeedTheme.colorScheme.container.background
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SeedSwitch(
                    checked = checked,
                    onCheckedChange = { checked = checked.not() },
                    text = "레이블 텍스트"
                )

                SeedSwitch(checked = checked,
                    onCheckedChange = { checked = checked.not() },
                    switchSize = SwitchSize.Small,
                    text = "레이블 텍스트"
                )
            }
        }
    }
}

object SeedSwitch {
    @Stable
    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean, selected: Boolean): State<Color>

        @Composable
        fun contentColor(enabled: Boolean, selected: Boolean): State<Color>
    }
}

object SeedSwitchDefaults {
    fun primary(
        checkedBackgroundColor: Color = SeedColorScheme().container.primary,
        uncheckedBackgroundColor: Color = white,
        checkedContentColor: Color = white,
        uncheckedContentColor: Color = gray30,
        disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
        disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
    ): SeedSwitch.Colors =
        SeedSwitchColors(
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
    ): SeedSwitch.Colors =
        SeedSwitchColors(
            checkedBackgroundColor = checkedBackgroundColor,
            uncheckedBackgroundColor = uncheckedBackgroundColor,
            checkedContentColor = checkedContentColor,
            uncheckedContentColor = uncheckedContentColor,
            disabledCheckedBackgroundColor = disabledCheckedBackgroundColor,
            disabledUncheckedContentColor = disabledUncheckedContentColor
        )
}

@Immutable
private data class SeedSwitchColors(
    private val checkedBackgroundColor: Color = SeedColorScheme().container.primary,
    private val uncheckedBackgroundColor: Color = white,
    private val checkedContentColor: Color = white,
    private val uncheckedContentColor: Color = gray30,
    private val disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(0.3f),
    private val disabledUncheckedBackgroundColor: Color = uncheckedBackgroundColor.copy(0.3f),
    private val disabledUncheckedContentColor: Color = uncheckedContentColor.copy(0.3f),
) : SeedSwitch.Colors {
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

