/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

// Define your space-themed colors here
private val SpaceDarkColorScheme = darkColorScheme(
    primary = Color(0xFF1A237E), // Dark blue, like the night sky
    onPrimary = Color(0xFFCE93D8), // Light purple, for elements on primary surfaces
    primaryContainer = Color(0xFF0D47A1), // A slightly different dark blue for containers
    onPrimaryContainer = Color(0xFFB39DDB), // Light purple for text/icons on primary containers
    secondary = Color(0xFF7C4DFF), // A vibrant color for accents, like a distant nebula
    onSecondary = Color(0xFFFFFFFF), // White for elements on secondary surfaces
    background = Color(0xFF000000), // True black for the background
    onBackground = Color(0xFFFFFFFF), // White for text/icons on the background
    surface = Color(0xFF121212), // Dark grey for card backgrounds and other surfaces
    onSurface = Color(0xFFE0E0E0) // Light grey for text/icons on surfaces
)

// You can define a light color scheme too if you want a different look for light theme
private val SpaceLightColorScheme = lightColorScheme(
    // Define light theme colors here, possibly with a dark blue background and light accents
)

@Composable
fun AffirmationsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> SpaceDarkColorScheme
        else -> SpaceLightColorScheme // Or LightColorScheme if you haven't defined a light space theme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = colorScheme.background.luminance() > 0.5
        }

    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

@Composable
fun Testtheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        SpaceLightColorScheme
    } else {
        SpaceDarkColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

