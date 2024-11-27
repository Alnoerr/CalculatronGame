package com.xren.calculatorgame

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xren.calculatorgame.data.levels
import com.xren.calculatorgame.ui.screen.CalculatorGameViewModel
import com.xren.calculatorgame.ui.screen.GameScreen
import com.xren.calculatorgame.ui.screen.GuideScreen
import com.xren.calculatorgame.ui.screen.LevelsScreen
import com.xren.calculatorgame.ui.screen.StartScreen

enum class CalculatronDestinations {
    Start, Guide, Levels, Game
}

@Composable
fun CalculatronGameNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: CalculatorGameViewModel = viewModel(factory = CalculatorGameViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState().value
    val maxLevel = viewModel.maxLevel.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = CalculatronDestinations.Start.name
    ) {
        composable(route = CalculatronDestinations.Start.name) {
            StartScreen(
                onStartGameClick = {
                    navController.navigate(CalculatronDestinations.Levels.name)
                },
                onClearProgressClick = viewModel::clearProgress,
                onGuideClick = {
                    navController.navigate(CalculatronDestinations.Guide.name)
                }
            )
        }

        composable(route = CalculatronDestinations.Guide.name) {
            GuideScreen(onNavigateBack = navController::navigateUp)
        }

        composable(route = CalculatronDestinations.Levels.name) {
            LevelsScreen(
                maxLevel = maxLevel,
                levels = levels,
                onNavigateBack = navController::navigateUp,
                onStartLevel = { level ->
                    viewModel.startLevel(level)
                    navController.navigate(CalculatronDestinations.Game.name)
                }
            )
        }

        composable(route = CalculatronDestinations.Game.name) {
            GameScreen(
                uiState = uiState,
                onAction = viewModel::updateState,
                onProceed = viewModel::proceed,
                onExit = {
                    navController.popBackStack(
                        route = CalculatronDestinations.Start.name,
                        inclusive = false
                    )
                }
            )
        }
    }
}