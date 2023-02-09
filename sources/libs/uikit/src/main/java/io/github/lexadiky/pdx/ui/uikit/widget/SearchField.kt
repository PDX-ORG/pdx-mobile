@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.ui.uikit.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.lexadiky.pdx.lib.uikit.R
import io.github.lexadiky.pdx.ui.uikit.theme.circular

@Composable
fun SearchField(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                placeholder = { Text(stringResource(id = R.string.uikit_search_field_placeholder)) },
                trailingIcon = { Icon(Icons.Default.Search, null) },
                shape = SearchBarDefaults.inputFieldShape,
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(top = 0.dp, bottom = 0.dp),
                container = {},
                colors = SearchBarDefaults.inputFieldColors(),
                interactionSource = remember { MutableInteractionSource() },
            )
        },
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.circular)
    )
}
