package com.xren.calculatorgame

import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xren.calculatorgame.ui.screen.GuideScreen
import com.xren.calculatorgame.ui.screen.StartScreen
import com.xren.calculatorgame.ui.theme.CalculatorGameTheme

enum class CalculatronDestinations {
    Start, Guide, Levels, Game
}

@Composable
fun CalculatronGameNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = CalculatronDestinations.Start.name
    ) {
        composable(route = CalculatronDestinations.Start.name) {
            StartScreen(
                onStartGameClick = {
                    context.startActivity(Intent(context, LevelsActivity::class.java))
                },
                onClearProgressClick = {
//                val save = getSharedPreferences("Save", MODE_PRIVATE)
//                val editor: SharedPreferences.Editor = save.edit()
//                editor.putInt("level", 1)
//                editor.apply()
                },
                onGuideClick = {
                    navController.navigate(CalculatronDestinations.Guide.name)
                }
            )
        }

        composable(route = CalculatronDestinations.Guide.name) {
            GuideScreen(onNavigateBack = navController::navigateUp)
        }

//        composable(route = CalculatronDestinations.Levels.name) {
//            LevelsScreen({})
//        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatronGameTopBar(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    @StringRes title: Int? = null
) {
    CenterAlignedTopAppBar(
        title = { if (title != null) Text(stringResource(title)) },
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