package com.greenlabsfin.design.component.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import com.greenlabsfin.design.core.GfTheme
import com.greenlabsfin.design.core.color.GfColorScheme
import com.greenlabsfin.design.core.color.gray30
import com.greenlabsfin.design.core.color.gray40
import com.greenlabsfin.design.core.color.white

@Stable
interface ControlColors {
    @Composable
    fun controlColor(enabled: Boolean, selected: Boolean): State<Color>

    @Composable
    fun controlDotColor(enabled: Boolean, selected: Boolean): State<Color>
}

object GFControl {
    object Colors {
        val radioPrimary: ControlColors
            @Composable
            get() = GFControlColors(
                checkedBackgroundColor = GfTheme.colorScheme.contents.primary
            )

        val switchPrimary: ControlColors
            @Composable
            get() = GFControlColors(
                checkedBackgroundColor = GfTheme.colorScheme.contents.primary,
                unCheckedBackgroundColor = gray40
            )

        @Composable
        fun custom(
            checkedBackgroundColor: Color = GfColorScheme().container.primary,
            unCheckedBackgroundColor: Color = white,
            checkedInnerItemColor: Color = white,
            uncheckedInnerItemColor: Color = gray30,
            disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(alpha = 0.3f),
            disabledUncheckedInnerItemColor: Color = uncheckedInnerItemColor.copy(0.3f),
        ): ControlColors =
            GFControlColors(
                checkedBackgroundColor,
                unCheckedBackgroundColor,
                checkedInnerItemColor,
                uncheckedInnerItemColor,
                disabledCheckedBackgroundColor,
                disabledUncheckedInnerItemColor
            )
    }
}

@Immutable
private data class GFControlColors(
    private val checkedBackgroundColor: Color = GfColorScheme().container.primary,
    private val unCheckedBackgroundColor: Color = white,
    private val checkedInnerItemColor: Color = white,
    private val uncheckedInnerItemColor: Color = gray30,
    private val disabledUncheckedBackgroundColor: Color = unCheckedBackgroundColor.copy(alpha = 0.3f),
    private val disabledCheckedBackgroundColor: Color = checkedBackgroundColor.copy(alpha = 0.3f),
    private val disabledUncheckedInnerItemColor: Color = uncheckedInnerItemColor.copy(0.3f),
) : ControlColors {
    @Composable
    override fun controlColor(enabled: Boolean, selected: Boolean): State<Color> =
        rememberUpdatedState(
            if (enabled) {
                if (selected) checkedBackgroundColor else unCheckedBackgroundColor
            } else {
                if (selected) disabledCheckedBackgroundColor else disabledUncheckedBackgroundColor
            }
        )

    @Composable
    override fun controlDotColor(enabled: Boolean, selected: Boolean): State<Color> =
        rememberUpdatedState(
            if (enabled) {
                if (selected) checkedInnerItemColor else uncheckedInnerItemColor
            } else {
                if (selected) checkedInnerItemColor else disabledUncheckedInnerItemColor
            }
        )

}