package com.greenlabsfin.design.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.greenlabsfin.design.core.color.GfColorScheme
import com.greenlabsfin.design.core.typo.GfTypoScheme

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
fun GfTheme(
    colorScheme: GfColorScheme = GfColorScheme(),
    typoScheme: GfTypoScheme = GfTypoScheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalGfColorScheme provides colorScheme,
        LocalGfTypoScheme provides typoScheme,
        content = content
    )
}

object GfTheme {
    val colorScheme: GfColorScheme
        @Composable
        get() = LocalGfColorScheme.current

    val typoScheme: GfTypoScheme
        @Composable
        get() = LocalGfTypoScheme.current
}

val LocalGfColorScheme = staticCompositionLocalOf {
    GfColorScheme()
}

val LocalGfTypoScheme = staticCompositionLocalOf {
    GfTypoScheme()
}
