package com.xren.calculatorgame.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.xren.calculatorgame.CalculatronGameApplication
import com.xren.calculatorgame.data.CalculatronGameRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//data class CalculatorGameUiState(
//    val isShowingGuide: Boolean = false
//)

class CalculatorGameViewModel(
    private val repository: CalculatronGameRepository
) : ViewModel() {
    val level = repository.level.stateIn(
        scope = viewModelScope,
        initialValue = 1,
        started = SharingStarted.WhileSubscribed(5_000)
    )

    fun setLevel(level: Int) {
        viewModelScope.launch {
            repository.updateLevel(level)
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