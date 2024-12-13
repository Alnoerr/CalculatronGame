package com.xren.calculatorgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme
import com.yandex.mobile.ads.common.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            MobileAds.initialize(this@MainActivity) {}
        }

        enableEdgeToEdge()
        setContent {
            CalculatorGameTheme {
                CalculatronGameNavGraph()
            }
        }
    }
}