package com.trent.poketchwatch.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.SurfaceHolder
import androidx.wear.watchface.CanvasWatchFaceService
import androidx.wear.watchface.WatchFaceStyle
import com.trent.poketchwatch.presentation.theme.getDrawableResource
import java.time.ZonedDateTime

class PoketchWatchFaceService : CanvasWatchFaceService() {

    override fun createEngine(): Engine {
        return PoketchEngine()
    }

    private inner class PoketchEngine : CanvasWatchFaceService.Engine() {
        private val paint = Paint().apply {
            color = Color.WHITE
            textSize = 40f
            textAlign = Paint.Align.CENTER
        }

        override fun onCreate(holder: SurfaceHolder) {
            super.onCreate(holder)
            setWatchFaceStyle(
                WatchFaceStyle.Builder(this@PoketchWatchFaceService)
                    .build()
            )
        }

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            val now = ZonedDateTime.now()
            val timeText = String.format("%02d:%02d:%02d", now.hour, now.minute, now.second)

            // Set background color
            canvas.drawColor(Color.parseColor("#70B070")) // Light green color

            // Draw time using bitmap font
            val fontTable = BitmapFont.getCharToDrawableMap(1, applicationContext)
            drawBitmapText(canvas, timeText, bounds, fontTable)

            // Draw Pikachu sprite
            val pikachuDrawable = getDrawableResource(1, "pikachu", applicationContext)
            val drawable = applicationContext.resources.getDrawable(pikachuDrawable, null)
            drawable.setBounds(bounds.centerX() - 50, bounds.centerY() + 50, bounds.centerX() + 50, bounds.centerY() + 150)
            drawable.draw(canvas)
        }

        override fun onTimeTick() {
            super.onTimeTick()
            invalidate()
        }

        private fun drawBitmapText(canvas: Canvas, text: String, bounds: Rect, fontTable: Map<Char, Int>) {
            val charWidth = 40 // Set the width of each character
            val xPos = bounds.centerX() - (charWidth * text.length / 2)
            text.forEachIndexed { index, char ->
                val drawableId = fontTable[char] ?: return@forEachIndexed
                val drawable = applicationContext.resources.getDrawable(drawableId, null)
                drawable.setBounds(xPos + index * charWidth, bounds.centerY() - 20, xPos + (index + 1) * charWidth, bounds.centerY() + 20)
                drawable.draw(canvas)
            }
        }
    }
}