package com.xren.calculatorgame.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xren.calculatorgame.Level
import com.xren.calculatorgame.R
import com.xren.calculatorgame.levels
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme
import com.xren.calculatorgame.ui.theme.FireBrick
import com.xren.calculatorgame.ui.utils.CalculatronButton
import com.xren.calculatorgame.ui.utils.CalculatronGameTopBar

@Composable
fun LevelsScreen(
    currentLevel: Int,
    levels: Array<Level>,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        containerColor = FireBrick,
        topBar = {
            CalculatronGameTopBar(
                onNavigateBack = onNavigateBack,
                title = R.string.levels_title
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(128.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            itemsIndexed(levels) { index, level ->
                CalculatronButton(
                    text = index.inc().toString(),
                    onClick = {},
                    enabled = index.inc() <= currentLevel
                )
            }
        }
    }
}



@Preview
@Composable
private fun LevelsScreenPreview() {
    CalculatorGameTheme {
        LevelsScreen(7, levels, {})
    }
}