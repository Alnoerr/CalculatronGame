package com.xren.calculatorgame.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xren.calculatorgame.CalculatronGameApplication
import com.xren.calculatorgame.data.CalculatronGameRepository
import com.xren.calculatorgame.data.Level
import com.xren.calculatorgame.data.levels
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.abs

data class GameUiState(
    val level: Level,
    val state: State = State.NORMAL,
    val currentNumber: Int = level.start,
    val currentMoves: Int = level.moves
)

enum class State {
    NORMAL, PAUSE, WIN, LOSE
}

class CalculatorGameViewModel(
    private val repository: CalculatronGameRepository
) : ViewModel() {
    val maxLevel = repository.level.stateIn(
        scope = viewModelScope,
        initialValue = 1,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    private val _uiState = MutableStateFlow(GameUiState(levels[maxLevel.value]))
    val uiState = _uiState.asStateFlow()

    fun startLevel(level: Level) {
        _uiState.update {
            GameUiState(
                level = level
            )
        }
    }

    fun clearProgress() {
        viewModelScope.launch {
            repository.clearProgress()
        }
    }

    private fun incrementLevel() {
        viewModelScope.launch {
            repository.incrementLevel()
        }
    }

    private fun togglePause() {
        _uiState.update {
            it.copy(
                state = if (it.state == State.PAUSE) State.NORMAL else State.PAUSE
            )
        }
    }

    private fun checkState() {
        if (_uiState.value.currentNumber == _uiState.value.level.target) {
            if (_uiState.value.level.id == maxLevel.value) {
                incrementLevel()
            }
            _uiState.update {
                GameUiState(
                    state = State.WIN,
                    level = levels[if (it.level.id >= levels.size) 0 else it.level.id]
                )
            }
        } else if (_uiState.value.currentMoves == 0) {
            _uiState.update {
                GameUiState(
                    state = State.LOSE,
                    level = levels[it.level.id.dec()]
                )
            }
        }
    }

    fun proceed() {
        _uiState.update {
            it.copy(
                state = State.NORMAL
            )
        }
    }

    fun updateState(action: String) {
        val op = action.first()
        if (op in "COPT") {
            if (op == 'C') {
                _uiState.update {
                    GameUiState(
                        level = it.level,
                        currentNumber = it.level.start,
                        currentMoves = it.level.moves
                    )
                }
            } else if (op == 'O') {
                togglePause()
            }
        } else {
            val num = action.drop(1).toIntOrNull() ?: 0
            _uiState.update {
                it.copy(
                    currentMoves = it.currentMoves - 1,
                    currentNumber = when (op) {
                        '+' -> it.currentNumber + num
                        '-' -> it.currentNumber - num
                        '*' -> it.currentNumber * num
                        '/' -> it.currentNumber / num
                        '%' -> it.currentNumber % num
                        '<' -> it.currentNumber / 10
                        '|' -> abs(it.currentNumber)
                        else -> it.level.target
                    }
                )
            }
            checkState()
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as CalculatronGameApplication)
                CalculatorGameViewModel(application.repository)
            }
        }
    }
}