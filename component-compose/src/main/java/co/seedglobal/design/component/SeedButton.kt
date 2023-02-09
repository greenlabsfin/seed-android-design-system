package co.seedglobal.design.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonElevation
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import co.seedglobal.design.core.SeedTheme
import co.seedglobal.design.core.color.SeedColorScheme
import co.seedglobal.design.core.color.gray90

@Composable
fun SeedButton(
    modifier: Modifier = Modifier,
    size: SeedButton.Size,
    colors: SeedButton.Colors,
    enabled: Boolean = true,
    text: String? = null,
    leftIcon: Painter? = null,
    rightIcon: Painter? = null,
    count: Int? = null,
    countColors: SeedCountColors? = null,
    elevation: ButtonElevation? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {

    val contentColor by colors.contentColor(enabled = enabled)
    val contentPaddingModifier: Modifier = text?.let {
        modifier.padding(size.textContentPadding)
    } ?: modifier.padding(size.iconContentPadding)

    val surfaceColor = remember { Animatable(Color.Transparent) }
    val isHovered = interactionSource.collectIsHoveredAsState()
    val isPressed = interactionSource.collectIsPressedAsState()

    val localDensity = LocalDensity.current
    var estimatedSize by remember {
        mutableStateOf(IntSize(0, 0))
    }

    val localDensitySizeModifier = with(localDensity) {
        Modifier.size(
            width = estimatedSize.width.toDp(),
            height = estimatedSize.height.toDp()
        )
    }

    when {
        isPressed.value -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(gray90.copy(.24f))
            }
        }
        isHovered.value -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(gray90.copy(.12f))
            }
        }
        else -> {
            LaunchedEffect(Unit) {
                surfaceColor.animateTo(Color.Transparent)
            }
        }
    }

    SeedSurface(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
            .defaultMinSize(
                minWidth = text?.let {
                    size.minimumWidth
                } ?: 0.dp
            )
            .height(size.displayPixel)
            .onGloballyPositioned {
                estimatedSize = IntSize(
                    width = it.size.width,
                    height = it.size.height
                )
            },
        shape = RoundedCornerShape(size.radius),
        color = colors.backgroundColor(enabled = enabled).value,
        border = BorderStroke(1.dp, colors.borderColor(enabled = enabled).value),
        elevation = elevation?.elevation(
            enabled = enabled,
            interactionSource = interactionSource
        )?.value ?: 0.dp,
    ) {
        ProvideTextStyle(value = size.typography) {
            Row(
                modifier = Modifier
                    .then(
                        contentPaddingModifier
                    ),
                horizontalArrangement = Arrangement.spacedBy(
                    space = 4.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                leftIcon?.let {
                    SeedIcon(
                        modifier = modifier.size(
                            size = text?.let {
                                size.textIconSize
                            } ?: size.onlyIconSize
                        ),
                        painter = it,
                        contentDescription = "SeedButton LeftIcon",
                        tint = contentColor
                    )
                }
                text?.let {
                    SeedText(
                        text = it,
                        style = size.typography,
                        overflow = TextOverflow.Ellipsis,
                        color = contentColor
                    )
                }
                count?.let {
                    countColors?.let {
                        SeedCount(
                            count = count,
                            colors = countColors,
                            enabled = enabled
                        )
                    }
                }
                rightIcon?.let {
                    SeedIcon(
                        modifier = modifier.size(
                            size = text?.let {
                                size.textIconSize
                            } ?: size.onlyIconSize
                        ),
                        painter = it,
                        contentDescription = "SeedButton RightIcon",
                        tint = contentColor
                    )
                }
            }
        }

        SeedSurface(
            modifier = Modifier.then(
                localDensitySizeModifier
            ),
            color = surfaceColor.value,
            shape = RoundedCornerShape(size.radius)
        ) {}
    }
}

object SeedButton {
    enum class Size(val displayPixel: Dp) {
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

        internal val textContentPadding: PaddingValues
            @Composable
            get() = PaddingValues(
                horizontal = 12.dp,
                vertical = when (this) {
                    XLarge -> 15.5.dp
                    Large -> 13.5.dp
                    Medium -> 11.dp
                    Small -> 8.5.dp
                    XSmall -> 4.5.dp
                }
            )

        internal val iconContentPadding: PaddingValues
            @Composable
            get() = PaddingValues(
                when (this) {
                    XLarge -> 16.dp
                    Large -> 14.dp
                    Medium -> 12.dp
                    Small -> 10.dp
                    XSmall -> 8.dp
                }
            )

        internal val textIconSize: Dp
            @Composable
            get() = when (this) {
                XLarge, Large -> 24.dp
                Medium, Small -> 20.dp
                XSmall -> 16.dp
            }

        internal val onlyIconSize: Dp
            @Composable
            get() = when (this) {
                XLarge -> 32.dp
                Large -> 28.dp
                Medium -> 24.dp
                Small -> 20.dp
                XSmall -> 16.dp
            }

        internal val minimumWidth: Dp
            @Composable
            get() = when (this) {
                XLarge -> 88.dp
                Large -> 81.dp
                Medium -> 76.dp
                Small, XSmall -> 62.dp
            }
    }

    interface Colors {
        @Composable
        fun backgroundColor(enabled: Boolean): State<Color>

        @Composable
        fun contentColor(enabled: Boolean): State<Color>

        @Composable
        fun borderColor(enabled: Boolean): State<Color>
    }
}

object SeedButtonDefaults {
    object Colors {
        @Composable
        fun containerPrimary(
            backgroundColor: Color = SeedTheme.colorScheme.container.primary,
            contentColor: Color = SeedTheme.colorScheme.contents.onPrimary,
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun outlinePrimary(
            backgroundColor: Color = SeedTheme.colorScheme.container.neutralPrimary,
            contentColor: Color = SeedTheme.colorScheme.container.primary,
            borderColor: Color = SeedTheme.colorScheme.container.primary,
            disabledBorderColor: Color = SeedTheme.colorScheme.container.neutralTertiary.copy(
                alpha = 0.6f
            ),
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            borderColor = borderColor,
            disabledBorderColor = disabledBorderColor
        )

        @Composable
        fun tintPrimary(
            backgroundColor: Color = SeedTheme.colorScheme.container.secondary,
            contentColor: Color = SeedTheme.colorScheme.container.primary,
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun outlineNeutral(
            backgroundColor: Color = SeedTheme.colorScheme.container.neutralPrimary,
            contentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
            borderColor: Color = SeedTheme.colorScheme.container.outline,
            disabledBorderColor: Color = SeedTheme.colorScheme.contents.neutralTertiary.copy(
                alpha = 0.6f
            ),
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            borderColor = borderColor,
            disabledBorderColor = disabledBorderColor
        )

        @Composable
        fun tintNeutral(
            backgroundColor: Color = SeedTheme.colorScheme.contents.neutralSecondary,
            contentColor: Color = SeedTheme.colorScheme.contents.neutralPrimary,
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun containerNegative(
            backgroundColor: Color = SeedTheme.colorScheme.container.error,
            contentColor: Color = SeedTheme.colorScheme.contents.onPrimary,
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )

        @Composable
        fun tintNegative(
            backgroundColor: Color = SeedTheme.colorScheme.container.error,
            contentColor: Color = SeedTheme.colorScheme.contents.error,
        ): SeedButton.Colors = SeedButtonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    }

    @Composable
    fun buttonColors(
        backgroundColor: Color,
        contentColor: Color,
        disabledBackgroundColor: Color =
            SeedTheme.colorScheme.container.neutralSecondary.copy(alpha = 0.6f),
        disabledContentColor: Color =
            SeedTheme.colorScheme.contents.neutralPrimary.copy(alpha = 0.6f),
        borderColor: Color = Color.Transparent,
        disabledBorderColor: Color = Color.Transparent,
    ): SeedButton.Colors =
        SeedButtonColors(
            backgroundColor,
            contentColor,
            disabledBackgroundColor,
            disabledContentColor,
            borderColor,
            disabledBorderColor
        )
}

@Immutable
private data class SeedButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color =
        SeedColorScheme().container.neutralSecondary.copy(alpha = 0.6f),
    private val disabledContentColor: Color =
        SeedColorScheme().contents.neutralPrimary.copy(alpha = 0.6f),
    private val borderColor: Color = Color.Transparent,
    private val disabledBorderColor: Color = Color.Transparent,
) : SeedButton.Colors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) contentColor else disabledContentColor)

    @Composable
    override fun borderColor(enabled: Boolean): State<Color> =
        rememberUpdatedState(if (enabled) borderColor else disabledBorderColor)
}

@Preview(widthDp = 250, heightDp = 470)
@Composable
fun DisabledButtonPreview() {
    SeedSurface {
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            SeedButton(
                text = "DISABLED",
                size = SeedButton.Size.Large,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                enabled = false,
                modifier = Modifier.fillMaxWidth()
            ) {}

            SeedButton(
                text = "ENABLED",
                size = SeedButton.Size.Large,
                colors = SeedButtonDefaults.Colors.containerPrimary(),
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            ) {}
        }

    }
}

