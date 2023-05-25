@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.lexadiky.pdx.library.uikit.widget

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
import io.github.lexadiky.pdx.library.uikit.UikitString
import io.github.lexadiky.pdx.library.uikit.theme.circular

@Composable
fun SearchField(text: String, onTextChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = text,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                placeholder = { Text(stringResource(id = UikitString.uikit_search_field_placeholder)) },
                trailingIcon = { Icon(Icons.Default.Search, null) },
                shape = SearchBarDefaults.inputFieldShape,
                colors = SearchBarDefaults.inputFieldColors(),
                contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(top = 0.dp, bottom = 0.dp),
                container = {},
            )
        },
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.shapes.circular)
    )
}
