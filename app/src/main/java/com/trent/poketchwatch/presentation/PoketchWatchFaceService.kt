package com.trent.poketchwatch.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.SurfaceHolder
import androidx.wear.watchface.CanvasWatchFaceService
import androidx.wear.watchface.WatchFaceService
import androidx.wear.watchface.style.WatchFaceStyle
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
                    .setAcceptsTapEvents(true)
                    .build()
            )
        }

        override fun onDraw(canvas: Canvas, bounds: Rect) {
            val now = ZonedDateTime.now()
            val timeText = "${now.hour}:${now.minute}:${now.second}"

            // Set background color
            canvas.drawColor(Color(0xFF70B070)) // Light green color

            // Draw time using bitmap font
            val fontTable = BitmapFont.getCharToDrawableMap(1, applicationContext)
            drawBitmapText(canvas, timeText, bounds, fontTable)

            // Draw Pikachu sprite
            val pikachuDrawable = getDrawableResource(1, "pikachu", applicationContext)
            val drawable = resources.getDrawable(pikachuDrawable, null)
            drawable.setBounds(bounds.centerX() - 50, bounds.centerY() + 50, bounds.centerX() + 50, bounds.centerY() + 150)
            drawable.draw(canvas)
        }

        override fun onTimeTick() {
            super.onTimeTick()
            invalidate()
        }

        override fun onAmbientModeChanged(inAmbientMode: Boolean) {
            super.onAmbientModeChanged(inAmbientMode)
            if (inAmbientMode) {
                paint.color = Color.GRAY
            } else {
                paint.color = Color.WHITE
            }
            invalidate()
        }

        override fun onTapCommand(tapType: Int, x: Int, y: Int, eventTime: Long) {
            when (tapType) {
                WatchFaceService.TAP_TYPE_TOUCH -> {
                    // Handle tap event
                }
                WatchFaceService.TAP_TYPE_TAP -> {
                    // Handle tap event
                }
                WatchFaceService.TAP_TYPE_TOUCH_CANCEL -> {
                    // Handle tap event
                }
            }
            invalidate()
        }

        private fun drawBitmapText(canvas: Canvas, text: String, bounds: Rect, fontTable: Map<Char, Int>) {
            val charWidth = 40 // Set the width of each character
            val xPos = bounds.centerX() - (charWidth * text.length / 2)
            text.forEachIndexed { index, char ->
                val drawableId = fontTable[char] ?: return@forEachIndexed
                val drawable = resources.getDrawable(drawableId, null)
                drawable.setBounds(xPos + index * charWidth, bounds.centerY() - 20, xPos + (index + 1) * charWidth, bounds.centerY() + 20)
                drawable.draw(canvas)
            }
        }
    }
}