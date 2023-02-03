package co.seedglobal.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.seedglobal.design.component.util.SeedPreview
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.SeedColorScheme
import co.seedglobal.design.core.color.gray90
import co.seedglobal.design.core.color.white
import co.seedglobal.design.core.typo.SeedTypoScheme

object SeedCountDefaults {
    @Composable
    fun primaryColors(
        textColor: Color = SeedTheme.colorScheme.contents.onPrimary,
        backgroundColor: Color = SeedTheme.colorScheme.contents.primary,
    ): SeedCountColors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor
    )

    @Composable
    fun secondaryColors(
        textColor: Color = SeedTheme.colorScheme.contents.primary,
        backgroundColor: Color = SeedTheme.colorScheme.container.secondary,
    ): SeedCountColors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor
    )

    @Composable
    fun neutralColors(
        textColor: Color = SeedTheme.colorScheme.contents.onInverse,
        backgroundColor: Color = SeedTheme.colorScheme.container.inverse,
    ): SeedCountColors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor
    )

    @Composable
    fun errorPrimaryColors(
        textColor: Color = SeedTheme.colorScheme.contents.error,
        backgroundColor: Color = SeedTheme.colorScheme.contents.onPrimary,
    ): SeedCountColors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor
    )

    @Composable
    fun errorSecondaryColors(
        textColor: Color = SeedTheme.colorScheme.contents.onPrimary,
        backgroundColor: Color = SeedTheme.colorScheme.contents.error,
    ): SeedCountColors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor
    )

    @Composable
    fun countColors(
        textColor: Color = Color.Unspecified,
        backgroundColor: Color = Color.Unspecified,
    ): SeedCountColors = DefaultSeedCountColors(
        textColor = textColor,
        backgroundColor = backgroundColor,
    )

    @Composable
    fun getByButtonColor(buttonColor: SeedButton.Colors): SeedCountColors =
        when (buttonColor) {
            SeedButtonDefaults.Colors.containerPrimary() -> secondaryColors()
            SeedButtonDefaults.Colors.outlinePrimary() -> primaryColors()
            SeedButtonDefaults.Colors.tintPrimary() -> primaryColors()
            SeedButtonDefaults.Colors.outlineNeutral() -> neutralColors()
            SeedButtonDefaults.Colors.tintNeutral() -> neutralColors()
            SeedButtonDefaults.Colors.containerNegative() -> errorPrimaryColors()
            SeedButtonDefaults.Colors.tintNegative() -> errorSecondaryColors()
            else -> primaryColors()
        }
}


@Stable
interface SeedCountColors {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun textColor(enabled: Boolean): State<Color>

    @Composable
    fun textStyle(textStyle: TextStyle, enabled: Boolean): State<TextStyle>
}


@Immutable
private data class DefaultSeedCountColors(
    val textColor: Color,
    val backgroundColor: Color,
) : SeedCountColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) backgroundColor else gray90.copy(.3f))

    @Composable
    override fun textColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) textColor else white)

    @Composable
    override fun textStyle(textStyle: TextStyle, enabled: Boolean): State<TextStyle> =
        rememberUpdatedState(
            textStyle.merge(
                TextStyle(
                    color = if (enabled) textColor else textColor.copy(alpha = .3f)
                )
            )
        )
}

object SeedCount {
    enum class Size(val value: Dp) {
        Large(16.dp),
        Medium(14.dp),
        Small(12.dp),
        ;

        val fontStyle: TextStyle
            get() = when (this) {
                Large -> SeedTypoScheme.custom(
                    size = 12.sp,
                    weight = FontWeight.Medium
                )
                Medium -> SeedTypoScheme.custom(
                    size = 10.sp,
                    weight = FontWeight.Medium
                )
                Small -> SeedTypoScheme.custom(
                    size = 8.sp,
                    weight = FontWeight.Medium
                )
            }
    }
}

@Composable
internal fun SeedCount(
    count: Int,
    colors: SeedCountColors,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    size: SeedCount.Size = SeedCount.Size.Large,
) {
    val backgroundColor by colors.backgroundColor(enabled = enabled)
    val textColor by colors.textColor(enabled = enabled)
    val text = count.toString()

    Box(
        modifier = modifier
            .defaultMinSize(minWidth = size.value, minHeight = size.value)
            .clip(CircleShape)
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = text,
            style = size.fontStyle,
            color = textColor,
        )
    }
}


@SeedPreview
@Composable
fun SeedCountPreview() {
    SeedTheme(colorScheme = SeedColorScheme.default(isSystemInDarkTheme())) {
        SeedSurface(color = SeedTheme.colorScheme.container.background) {
            Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                SeedCount(count = 3, colors = SeedCountDefaults.primaryColors())
                SeedCount(count = 33, colors = SeedCountDefaults.secondaryColors())
                SeedCount(
                    size = SeedCount.Size.Small,
                    count = 3,
                    colors = SeedCountDefaults.neutralColors()
                )
                SeedCount(
                    size = SeedCount.Size.Small,
                    count = 333,
                    colors = SeedCountDefaults.errorPrimaryColors()
                )
                SeedCount(
                    size = SeedCount.Size.Medium,
                    count = 3,
                    colors = SeedCountDefaults.errorSecondaryColors(),
                )
            }
        }
    }
}
