package com.orly.whispers.ui.popularfeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.orly.whispers.data.repository.popularfeed.IPopularFeedRepository

class PopularFeedViewModel(
    popularFeedRepository: IPopularFeedRepository
) : ViewModel() {

    val whispers =
        popularFeedRepository
            .fetchPopularFeedWhispers(LIMIT)
            .cachedIn(viewModelScope)

    companion object {
        private const val LIMIT = 30
    }
}