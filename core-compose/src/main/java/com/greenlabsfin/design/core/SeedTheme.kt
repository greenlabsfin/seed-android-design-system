package com.greenlabsfin.design.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import com.greenlabsfin.design.core.color.SeedColorScheme
import com.greenlabsfin.design.core.color.gray90
import com.greenlabsfin.design.core.typo.SeedTypoScheme

/**
 * Greenlabs Financial Theming refers to the customization of Greenlabs Financial app to better
 * reflect product's brand.
 *
 *    @Composable
 *    fun FooTheme(
 *        darkTheme: Boolean = isSystemInDarkTheme(),
 *        content: @Composable () -> Unit,
 *    ) {
 *      val colorScheme = GfColorScheme.default(darkTheme)
 *      val typoScheme = TODO
 *      GfTheme(
 *          colorScheme = colorScheme,
 *          typoScheme = typoScheme,
 *          content = content,
 *      )
 *    }
 *
 * @param colorScheme A definition of the color theme for this hierarchy
 * @param typoScheme A set of text styles to be used as this hierarchy's typography system
 *
 */
@Composable
fun SeedTheme(
    colorScheme: SeedColorScheme = SeedColorScheme(),
    typoScheme: SeedTypoScheme = SeedTypoScheme(),
    backgroundColor: Color = SeedColorScheme().container.background,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalSeedColorScheme provides colorScheme,
        LocalSeedTypoScheme provides typoScheme,
        LocalSeedContainerColor provides colorScheme.contents.neutralPrimary,
        LocalSeedTextStyle provides typoScheme.body.mediumRegular,
        LocalSeedBackgroundColor provides backgroundColor,
        content = content
    )
}

object SeedTheme {
    val colorScheme: SeedColorScheme
        @Composable
        get() = LocalSeedColorScheme.current

    val typoScheme: SeedTypoScheme
        @Composable
        get() = LocalSeedTypoScheme.current
}

val LocalSeedColorScheme = staticCompositionLocalOf {
    SeedColorScheme()
}

val LocalSeedTypoScheme = staticCompositionLocalOf {
    SeedTypoScheme()
}

val LocalSeedContainerColor = staticCompositionLocalOf { gray90 }

val LocalSeedTextStyle =
    compositionLocalOf(structuralEqualityPolicy()) { SeedTypoScheme().body.mediumRegular }

val LocalSeedBackgroundColor = compositionLocalOf { SeedColorScheme().container.background }
