package com.xren.calculatorgame.ui.utils

import android.view.MotionEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xren.calculatorgame.R
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CalculatronButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 128.dp,
    height: Dp = 100.dp,
    enabled: Boolean = true
) {
    var pressed by remember { mutableStateOf(false) }

    Row(
        modifier
            .semantics { role = Role.Button }
            .pointerInteropFilter {
                if (enabled) {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            pressed = true
                        }

                        MotionEvent.ACTION_UP -> {
                            pressed = false
                            onClick()
                        }

                        MotionEvent.ACTION_CANCEL -> {
                            pressed = false
                        }
                    }
                }
                true
            }
            .size(
                width = width,
                height = height
            )
            .paint(
                painter = painterResource(
                    if (enabled) {
                        if (pressed) {
                            R.drawable.calculator_button_grey_pressed_fullsize
                        } else {
                            R.drawable.calculator_button_grey_fullsize
                        }
                    } else {
                        R.drawable.calculator_button_empty_fullsize
                    }
                ),
                contentScale = ContentScale.FillBounds
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text)
    }
}


@Preview
@Composable
private fun CalculatronButton256Preview() {
    CalculatorGameTheme {
        CalculatronButton(
            text = stringResource(R.string.start_game),
            onClick = {},
            width = 256.dp
        )
    }
}

@Preview
@Composable
private fun CalculatronButton128Preview() {
    CalculatorGameTheme {
        CalculatronButton(
            text = "1",
            onClick = {}
        )
    }
}