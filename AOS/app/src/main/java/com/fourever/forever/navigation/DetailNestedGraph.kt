package com.fourever.forever.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.fourever.forever.presentation.getquestion.GetQuestionViewModel
import com.fourever.forever.presentation.getquestion.GetSingleQuestionScreen
import com.fourever.forever.presentation.getsummary.GetSummaryScreen
import com.fourever.forever.presentation.getsummary.GetSummaryViewModel

fun NavGraphBuilder.detailGraph(
    navController: NavController,
    navActions: ForeverNavActions
) {
    navigation(
        startDestination = Screen.GetSummary.route, route = Screen.Detail.route
    ) {
        composable(
            Screen.GetSummary.route,
            arguments = listOf(
                navArgument(ForeverDestinationArgs.DOCUMENT_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            val getSummaryViewModel = hiltViewModel<GetSummaryViewModel>()
            val summaryUiState by getSummaryViewModel.summaryUiState.collectAsState()
            val questionListUiState by getSummaryViewModel.questionListUiState.collectAsState()

            val documentId = it.arguments?.getInt(ForeverDestinationArgs.DOCUMENT_ID_ARG) ?: 0

            GetSummaryScreen(
                summaryUiState = summaryUiState,
                questionListUiState = questionListUiState,
                getSummary = { (getSummaryViewModel::getSummary)(documentId) },
                getQuestionList = { (getSummaryViewModel::getQuestionList)(documentId) },
                navigateToGetSingleQuestion = { questionId ->
                    navActions.navigateToGetSingleQuestion(
                        documentId = documentId,
                        fileName = summaryUiState.title,
                        questionId = questionId
                    )
                },
                navigateToGetAllQuestion = {
                    navActions.navigateToGetAllQuestion(
                        documentId = documentId,
                        fileName = summaryUiState.title,
                        questionSize = questionListUiState.questionList.size
                    )
                }
            )
        }
        composable(
            Screen.GetSingleQuestion.route,
            arguments = listOf(
                navArgument(ForeverDestinationArgs.DOCUMENT_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(ForeverDestinationArgs.QUESTION_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(ForeverDestinationArgs.FILE_NAME_ARG) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            /* TODO: 동알한 ViewModel을 쓰는 다른 두 Screen은 그 ViewModel을 반드시 공유해야 할까? */
            val getQuestionViewModel = hiltViewModel<GetQuestionViewModel>()
            val questionUiState by getQuestionViewModel.questionUiState.collectAsState()

            val documentId = it.arguments?.getInt(ForeverDestinationArgs.DOCUMENT_ID_ARG) ?: 0
            val questionId = it.arguments?.getInt(ForeverDestinationArgs.QUESTION_ID_ARG) ?: 0
            val fileName = it.arguments?.getString(ForeverDestinationArgs.FILE_NAME_ARG) ?: ""

            GetSingleQuestionScreen(
                questionUiState = questionUiState,
                fileName = fileName,
                getQuestion = { (getQuestionViewModel::getQuestion)(documentId, questionId) },
                navigateUp = { navController.navigateUp() }
            )
        }

        composable(
            Screen.GetAllQuestions.route,
            arguments = listOf(
                navArgument(ForeverDestinationArgs.DOCUMENT_ID_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument(ForeverDestinationArgs.FILE_NAME_ARG) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(ForeverDestinationArgs.QUESTION_SIZE_ARG) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) {
            // GetAllQuestionScreen()
        }
    }
}