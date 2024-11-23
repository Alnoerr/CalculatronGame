package com.xren.calculatorgame.ui.screen

import android.view.MotionEvent
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.xren.calculatorgame.ui.theme.Bone
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme

@Composable
fun StartScreen(
    onStartGameClick: () -> Unit,
    onClearProgressClick: () -> Unit,
    onGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Bone)
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.main_title),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(Modifier.height(64.dp))

        CalculatronButton(
            text = R.string.start_game,
            onClick = onStartGameClick,
            width = 256.dp
        )

        Spacer(Modifier.height(32.dp))

        CalculatronButton(
            text = R.string.clear_progress,
            onClick = onClearProgressClick,
            width = 256.dp
        )

        Spacer(Modifier.height(32.dp))

        CalculatronButton(
            text = R.string.how_to_play,
            onClick = onGuideClick,
            width = 256.dp
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CalculatronButton(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp = 100.dp,
    height: Dp = 100.dp
) {
    var pressed by remember { mutableStateOf(false) }

    Row(
        modifier
            .semantics { role = Role.Button }
            .pointerInteropFilter {
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
                true
            }
            .size(
                width = width,
                height = height
            )
            .paint(
                painter = painterResource(
                    if (pressed) {
                        R.drawable.calculator_button_grey_pressed_fullsize
                    } else {
                        R.drawable.calculator_button_grey_fullsize
                    }
                ),
                contentScale = ContentScale.FillBounds
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(stringResource(text))
    }
}

@Preview
@Composable
private fun StartScreenPreview() {
    CalculatorGameTheme {
        Surface {
            StartScreen({}, {}, {})
        }
    }
}

@Preview
@Composable
private fun CalculatronButtonPreview() {
    CalculatorGameTheme {
        CalculatronButton(
            text = R.string.start_game,
            onClick = {},
            width = 256.dp
        )
    }
}