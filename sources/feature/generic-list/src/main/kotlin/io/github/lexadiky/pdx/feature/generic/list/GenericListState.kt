package io.github.lexadiky.pdx.feature.generic.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListBanner
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.library.errorhandler.UIError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class GenericListState<T : GenericListItem>(
    val items: List<T> = emptyList(),
    val banners: List<GenericListBanner<T>> = emptyList(),
    val query: SearchQuery<T>? = null,
    val searchActivated: Boolean = false,
    val searchAvailable: Boolean = false,
    val useAlternativeImages: Boolean = false,
    val uiError: UIError? = null
) {

    // TODO replace with normal background computation
    @Composable
    fun visibleItemsAsync(): List<T> {
        val result = remember { mutableStateOf(emptyList<T>()) }
        LaunchedEffect(query, items) {
            withContext(Dispatchers.Default) {
                result.value = query?.apply(items) ?: items
            }
        }

        return result.value
    }
}
