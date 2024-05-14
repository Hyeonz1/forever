package com.fourever.forever.presentation.generatequestion

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.fourever.forever.data.repository.FileRepository
import com.fourever.forever.presentation.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class GenerateQuestionUiState(
    val questionState: UiState = UiState.Empty,
    val errorMessage: String = "",

    val currentQuestion: String = "질문1",
    val currentAnswer: String = "답변1",
    val questionSaveStatus: MutableList<Boolean> = mutableStateListOf(false, false, false, false, false)
)

@HiltViewModel
class GenerateQuestionViewModel @Inject constructor(
    private val fileRepository: FileRepository
) : ViewModel() {
    private val _generateQuestionUiState = MutableStateFlow(GenerateQuestionUiState())
    val generateQuestionUiState: StateFlow<GenerateQuestionUiState> = _generateQuestionUiState.asStateFlow()

    fun toggleQuestionSaveStatus(questionIndex: Int) {
        val currentValue = generateQuestionUiState.value.questionSaveStatus[questionIndex]
        _generateQuestionUiState.value.questionSaveStatus[questionIndex] = !currentValue
    }
}