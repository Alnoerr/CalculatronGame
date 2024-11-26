package com.xren.calculatorgame.ui.utils

import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.xren.calculatorgame.R
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatronGameTopBar(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes title: Int? = null
) {
    CenterAlignedTopAppBar(
        title = {
            if (title != null) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = onNavigateBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_left),
                    contentDescription = stringResource(R.string.navigate_back),
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
private fun CalculatronGameTopBarPreview() {
    CalculatorGameTheme {
        CalculatronGameTopBar(
            onNavigateBack = {},
            title = R.string.levels_title
        )
    }
}