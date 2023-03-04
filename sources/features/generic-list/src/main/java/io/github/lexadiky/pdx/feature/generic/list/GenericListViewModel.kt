package io.github.lexadiky.pdx.feature.generic.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import arrow.core.zip
import io.github.lexadiky.pdx.domain.achievement.AchievementManager
import io.github.lexadiky.pdx.domain.achievement.library.ShinyShakeAchievement
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListBannerDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListItemDataSource
import io.github.lexadiky.pdx.feature.generic.list.domain.GenericListNavigator
import io.github.lexadiky.pdx.feature.generic.list.entity.GenericListItem
import io.github.lexadiky.pdx.feature.generic.list.entity.SearchQuery
import io.github.lexadiky.pdx.lib.errorhandler.UIError
import io.github.lexadiky.pdx.ui.uikit.util.ShakeDetector
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class GenericListViewModel<T : GenericListItem>(
    private val dataSource: GenericListItemDataSource<T>,
    private val bannerSource: GenericListBannerDataSource<T>,
    private val navigator: GenericListNavigator<T>,
    private val shakeDetector: ShakeDetector,
    private val achievementManager: AchievementManager,
    initialSearchQuery: SearchQuery<T>
) : ViewModel() {

    var state by mutableStateOf(
        GenericListState(query = initialSearchQuery, searchActivated = !initialSearchQuery.isEmpty)
    )
        private set

    fun refresh() {
        loadFreshData()
        observeShakeEvents()
    }

    fun toggleSearchMode() {
        state = state.copy(
            searchActivated = !state.searchActivated,
            query = null
        )
    }

    fun updateQuery(query: SearchQuery<T>) {
        state = state.copy(query = query)
    }

    fun openDetails(item: T) = viewModelScope.launch {
        navigator.navigateToDetails(item)
    }

    fun onTagClicked(item: T, tag: GenericListItem.Tag) = viewModelScope.launch {
        navigator.navigateToTag(item, tag)
    }

    fun setSearchAvailable(isAvailable: Boolean) {
        state = state.copy(searchAvailable = isAvailable)
    }

    fun hideError() {
        state = state.copy(uiError = null)
    }

    private fun loadFreshData() = viewModelScope.launch {
        state = when (val data = dataSource.load().zip(bannerSource.load())) {
            is Either.Left -> state.copy(uiError = UIError.default())
            is Either.Right -> state.copy(items = data.value.first, banners = data.value.second)
        }
    }

    private fun observeShakeEvents() {
        viewModelScope.launch {
            shakeDetector.events.collectLatest {
                achievementManager.give(ShinyShakeAchievement())
                state = state.copy(useAlternativeImages = !state.useAlternativeImages)
            }
        }
    }
}
