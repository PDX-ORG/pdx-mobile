package io.github.lexadiky.pdx.feature.type.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TypeDetailsPage(typeId: String) {
    Text(text = "Type: $typeId")
}