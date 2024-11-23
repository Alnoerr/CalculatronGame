package com.xren.calculatorgame.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.xren.calculatorgame.CalculatronGameTopBar
import com.xren.calculatorgame.R
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme

@Composable
fun GuideScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.calculator_help),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        CalculatronGameTopBar(onNavigateBack)
    }
}

@Preview
@Composable
private fun GuideScreenPreview() {
    CalculatorGameTheme {
        GuideScreen({})
    }
}