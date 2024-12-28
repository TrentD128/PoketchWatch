package com.trent.poketchwatch.presentation

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.SurfaceHolder
import androidx.wear.watchface.CanvasType
import androidx.wear.watchface.ComplicationSlotsManager
import androidx.wear.watchface.Renderer
import androidx.wear.watchface.WatchFace
import androidx.wear.watchface.WatchFaceService
import androidx.wear.watchface.WatchFaceType
import androidx.wear.watchface.WatchState
import androidx.wear.watchface.style.CurrentUserStyleRepository
import java.time.ZonedDateTime

class PoketchWatchFaceService : WatchFaceService() {

    override suspend fun createWatchFace(
        surfaceHolder: SurfaceHolder,
        watchState: WatchState,
        complicationSlotsManager: ComplicationSlotsManager,
        currentUserStyleRepository: CurrentUserStyleRepository
    ): WatchFace {
        return WatchFace(
            WatchFaceType.DIGITAL,
            object : Renderer.CanvasRenderer(
                surfaceHolder,
                currentUserStyleRepository,
                watchState,
                CanvasType.HARDWARE,
                16
            ) {
                private val paint = Paint().apply {
                    color = Color.WHITE
                    textSize = 40f
                    textAlign = Paint.Align.CENTER
                }

                override fun render(canvas: Canvas, bounds: Rect, zonedDateTime: ZonedDateTime) {
                    // Set background color
                    canvas.drawColor(Color.parseColor("#70B070")) // Light green color

                    // Draw time
                    val timeText = String.format("%02d:%02d:%02d", zonedDateTime.hour, zonedDateTime.minute, zonedDateTime.second)
                    canvas.drawText(timeText, bounds.centerX().toFloat(), bounds.centerY().toFloat(), paint)
                }

                override fun renderHighlightLayer(
                    canvas: Canvas,
                    bounds: Rect,
                    zonedDateTime: ZonedDateTime
                ) {
                    // Optional: implement highlight layer rendering if needed
                }
            }
        )
    }
}