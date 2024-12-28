package com.trent.poketchwatch.presentation.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.MaterialTheme

// Theme data class for dynamic theming
data class Theme(
    val color1: Color,
    val color2: Color,
    val color3: Color,
    val color4: Color
)

// Define themes with corresponding colors
val themes = mapOf(
    1 to Theme(Color(0xFF70B070), Color(0xFF508050), Color(0xFF385030), Color(0xFF102818)) // Green
)

// Function to dynamically resolve drawable resources
fun getDrawableResource(themeIndex: Int, assetName: String, context: Context): Int {
    val themeLetter = when (themeIndex) {
        1 -> "a"
        else -> "a" // Default to 'a' if index is out of range
    }

    // If assetName starts with "de", don't prepend the themeLetter
    val resourceName = if (assetName.startsWith("de")) {
        assetName
    } else {
        "${themeLetter}${assetName}"
    }

    return context.resources.getIdentifier(resourceName, "drawable", context.packageName)
}

// Dynamic theme composable
@Composable
fun SimplePoketchTheme(
    themeIndex: Int, // Index to pick the theme
    content: @Composable () -> Unit
) {
    // Get the selected theme or fallback to default
    val selectedTheme = themes[themeIndex] ?: themes[1]!!

    MaterialTheme(
        colors = androidx.wear.compose.material.Colors(
            primary = selectedTheme.color3,    // Text color
            secondary = selectedTheme.color4, // Accent or secondary elements
            background = selectedTheme.color1, // Main background color
            onPrimary = selectedTheme.color2  // Color for text/icons on primary
        ),
        content = content
    )
}