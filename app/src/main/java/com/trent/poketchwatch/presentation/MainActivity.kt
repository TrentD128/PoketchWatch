package com.trent.poketchwatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.TimeText
import androidx.wear.tooling.preview.devices.WearDevices
import com.trent.poketchwatch.R
import com.trent.poketchwatch.presentation.theme.PoketchWatchTheme
import com.trent.poketchwatch.presentation.theme.getDrawableResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WatchFaceApp()
        }
    }
}

@Composable
fun WatchFaceApp() {
    val context = LocalContext.current
    val themeIndex = 1 // Using themeIndex 1 for the green theme
    val pikachuDrawableRes = getDrawableResource(themeIndex, "pikachu", context)

    PoketchWatchTheme(themeIndex = themeIndex) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = pikachuDrawableRes),
                contentDescription = "Pikachu",
                modifier = Modifier.align(Alignment.Center)
            )
            TimeText(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WatchFaceApp()
}