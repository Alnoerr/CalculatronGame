package com.xren.calculatorgame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.xren.calculatorgame.R

val Pixeleum_48 = FontFamily(
    Font(R.font.pixeleum_48, FontWeight.Normal)
)

val Technology = FontFamily(
    Font(R.font.technology, FontWeight.Normal),
    Font(R.font.technology_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Pixeleum_48,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White,
        textAlign = TextAlign.Center
    ),
    bodyMedium = TextStyle(
        fontFamily = Pixeleum_48,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = PaleGreen
    ),
    displayLarge = TextStyle(
        fontFamily = Technology,
        fontWeight = FontWeight.Bold,
        fontSize = 80.sp,
        color = Color.Black
    ),
    displayMedium = TextStyle(
        fontFamily = Pixeleum_48,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    displaySmall = TextStyle(
        fontFamily = Pixeleum_48,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        textAlign = TextAlign.Center
    )
)