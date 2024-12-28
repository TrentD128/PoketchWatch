package com.trent.poketchwatch.presentation

import android.content.Context
import com.trent.poketchwatch.presentation.theme.getDrawableResource

object BitmapFont {
    fun getCharToDrawableMap(themeIndex: Int, context: Context): Map<Char, Int> {
        return mapOf(
            '0' to getDrawableResource(themeIndex, "time0", context),
            '1' to getDrawableResource(themeIndex, "time1", context),
            '2' to getDrawableResource(themeIndex, "time2", context),
            '3' to getDrawableResource(themeIndex, "time3", context),
            '4' to getDrawableResource(themeIndex, "time4", context),
            '5' to getDrawableResource(themeIndex, "time5", context),
            '6' to getDrawableResource(themeIndex, "time6", context),
            '7' to getDrawableResource(themeIndex, "time7", context),
            '8' to getDrawableResource(themeIndex, "time8", context),
            '9' to getDrawableResource(themeIndex, "time9", context),
            ':' to getDrawableResource(themeIndex, "timecolon", context),
            'A' to getDrawableResource(themeIndex, "time_am", context),
            'P' to getDrawableResource(themeIndex, "time_pm", context)
        )
    }
}