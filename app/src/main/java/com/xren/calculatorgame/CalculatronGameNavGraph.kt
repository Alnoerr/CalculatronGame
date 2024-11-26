package com.xren.calculatorgame

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xren.calculatorgame.ui.screen.CalculatorGameViewModel
import com.xren.calculatorgame.ui.screen.GuideScreen
import com.xren.calculatorgame.ui.screen.LevelsScreen
import com.xren.calculatorgame.ui.screen.StartScreen

enum class CalculatronDestinations {
    Start, Guide, Levels, Game, GameSecondary
}

@Composable
fun CalculatronGameNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: CalculatorGameViewModel = viewModel(factory = CalculatorGameViewModel.Factory)
) {
    NavHost(
        navController = navController,
        startDestination = CalculatronDestinations.Start.name
    ) {
        composable(route = CalculatronDestinations.Start.name) {
            StartScreen(
                onStartGameClick = {
                    navController.navigate(CalculatronDestinations.Levels.name)
                },
                onClearProgressClick = {
                    viewModel.setLevel(1)
                },
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
                currentLevel = 7,
                levels = levels,
                onNavigateBack = navController::navigateUp
            )
        }
    }
}