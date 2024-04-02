package com.fourever.forever.presentation.component.btmsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fourever.forever.R
import com.fourever.forever.presentation.component.buttons.LongColorBtn
import com.fourever.forever.ui.theme.foreverTypography

@Composable
fun QuestionListBtnSheet(/*TODO: questionList: List<String>*/) {
    Column {
        Column {
            Text(
                text = stringResource(id = R.string.question_list_title),
                style = foreverTypography.titleMedium,
                color = colorResource(id = R.color.paragraph)
            )
            Spacer(modifier = Modifier.size(BTM_SHEET_SPACE_BETWEEN_TITLE_AND_SUBTITLE.dp))
            Text(
                text = stringResource(id = R.string.question_list_subtitle),
                style = foreverTypography.bodySmall,
                color = colorResource(id = R.color.subtitle)
            )
        }
        Spacer(modifier = Modifier.size(BTM_SHEET_SPACE_BETWEEN_TITLE_AND_CONTENT.dp))
        /*TODO: questionList 가지고 QuestionCard 컴포넌트 추가*/
        LongColorBtn(
            text = stringResource(id = R.string.question_list_check_question_button),
            enabled = true
        )
    }
}

@Preview(showSystemUi = false, showBackground = false)
@Composable
private fun BtmSheetPreview() {
    MaterialTheme {
        QuestionListBtnSheet()
    }
}