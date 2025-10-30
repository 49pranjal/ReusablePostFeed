package com.example.reusabelpostfeed.composableComponents

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.res.colorResource
import com.example.reusabelpostfeed.R

@Composable
fun FeedTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme = if (darkTheme) {
        // ✅ Only load dark colors when darkTheme = true
        darkColorScheme(
            primary = colorResource(R.color.md_theme_dark_primary),
            onPrimary = colorResource(R.color.md_theme_dark_onPrimary),
            background = colorResource(R.color.md_theme_dark_background),
            onBackground = colorResource(R.color.md_theme_dark_onBackground)
        )
    } else {
        // ✅ Only load light colors when darkTheme = false
        lightColorScheme(
            primary = colorResource(R.color.md_theme_light_primary),
            onPrimary = colorResource(R.color.md_theme_light_onPrimary),
            background = colorResource(R.color.md_theme_light_background),
            onBackground = colorResource(R.color.md_theme_light_onBackground)
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}