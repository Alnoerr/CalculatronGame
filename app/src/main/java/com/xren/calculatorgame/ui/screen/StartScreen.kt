package com.xren.calculatorgame.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xren.calculatorgame.R
import com.xren.calculatorgame.ui.theme.Bone
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme
import com.xren.calculatorgame.ui.utils.CalculatronButton

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
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(Modifier.height(64.dp))

        CalculatronButton(
            text = stringResource(R.string.start_game),
            onClick = onStartGameClick,
            width = 256.dp
        )

        Spacer(Modifier.height(32.dp))

        CalculatronButton(
            text = stringResource(R.string.clear_progress),
            onClick = onClearProgressClick,
            width = 256.dp
        )

        Spacer(Modifier.height(32.dp))

        CalculatronButton(
            text = stringResource(R.string.how_to_play),
            onClick = onGuideClick,
            width = 256.dp
        )
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