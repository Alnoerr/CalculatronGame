package com.xren.calculatorgame.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xren.calculatorgame.R
import com.xren.calculatorgame.data.levels
import com.xren.calculatorgame.ui.theme.Bone
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme
import com.xren.calculatorgame.ui.theme.Chocolate
import com.xren.calculatorgame.ui.theme.FireBrick
import com.xren.calculatorgame.ui.theme.ForestGreen
import com.xren.calculatorgame.ui.utils.CalculatronButton

@Composable
fun GameScreen(
    uiState: GameUiState,
    onAction: (String) -> Unit,
    onProceed: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState.state) {
        State.NORMAL -> {
            GameContent(
                uiState = uiState,
                onAction = onAction,
                modifier = modifier
            )
        }
        State.PAUSE -> {
            SecondaryGameScreen(
                text = stringResource(R.string.menu),
                buttonText = stringResource(R.string.continue_game),
                backgroundColor = Chocolate,
                onProceed = onProceed,
                onExit = onExit,
                modifier = modifier
            )
        }
        State.WIN -> {
            SecondaryGameScreen(
                text = stringResource(R.string.level_passed),
                buttonText = stringResource(R.string.want2continueqm),
                backgroundColor = ForestGreen,
                onProceed = onProceed,
                onExit = onExit,
                modifier = modifier
            )
        }
        State.LOSE -> {
            SecondaryGameScreen(
                text = stringResource(R.string.level_failed),
                buttonText = stringResource(R.string.want2retreatqm),
                backgroundColor = FireBrick,
                onProceed = onProceed,
                onExit = onExit,
                modifier = modifier
            )
        }
    }
}

@Composable
fun GameContent(
    uiState: GameUiState,
    onAction: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Bone)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Box(Modifier.padding(top = 20.dp)) {
            Image(
                painter = painterResource(R.drawable.calculator_interface_fullsize),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = stringResource(R.string.moves, uiState.currentMoves),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    top = 104.dp,
                    start = 64.dp
                )
            )
            Text(
                text = stringResource(R.string.target, uiState.level.target),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(
                        top = 104.dp,
                        end = 108.dp
                    )
                    .align(Alignment.TopEnd)
            )
            Text(
                text = stringResource(R.string.level, uiState.level.id),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 56.dp)
                    .align(Alignment.BottomCenter)
            )
            Text(
                text = uiState.currentNumber.toString(),
                style = MaterialTheme.typography.displayLarge,
                maxLines = 1,
                modifier = Modifier
                    .padding(
                        top = 152.dp,
                        end = 64.dp
                    )
                    .align(Alignment.TopEnd)
            )
        }
        Spacer(Modifier.height(32.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            for (row in uiState.level.table) {
                for (op in row) {
                    item {
                        when (op) {
                            "" -> {
                                CalculatronButton(
                                    text = "",
                                    onClick = {},
                                    enabled = false,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                            "C" -> {
                                CalculatronButton(
                                    text = op,
                                    onClick = { onAction(op) },
                                    image = R.drawable.calculator_button_clear_fullsize,
                                    imagePressed = R.drawable.calculator_button_clear_pressed_fullsize,
                                    fontSize = 40.sp,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                            "OPT" -> {
                                CalculatronButton(
                                    text = "",
                                    onClick = { onAction(op) },
                                    image = R.drawable.calculator_button_options_fullsize,
                                    imagePressed = R.drawable.calculator_button_options_pressed_fullsize,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                            else -> {
                                CalculatronButton(
                                    text = op,
                                    onClick = { onAction(op) },
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SecondaryGameScreen(
    text: String,
    buttonText: String,
    backgroundColor: Color,
    onProceed: () -> Unit,
    onExit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(backgroundColor)
            .fillMaxSize()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = Color.White
        )
        Spacer(Modifier.height(200.dp))
        CalculatronButton(
            text = buttonText,
            onClick = onProceed,
            width = 256.dp
        )
        Spacer(Modifier.height(16.dp))
        CalculatronButton(
            text = stringResource(R.string.want2go2menuqm),
            onClick = onExit,
            width = 256.dp
        )
    }
}

@Preview
@Composable
private fun GameScreenPreview() {
    CalculatorGameTheme {
        GameScreen(
            uiState = GameUiState(
                level = levels.first()
            ),
            onAction = {},
            onProceed = {},
            onExit = {}
        )
    }
}

@Preview
@Composable
private fun SecondaryGameScreenPreview() {
    CalculatorGameTheme {
        SecondaryGameScreen(
            text = stringResource(R.string.level_passed),
            buttonText = stringResource(R.string.want2continueqm),
            backgroundColor = ForestGreen,
            onProceed = {},
            onExit = {}
        )
    }
}