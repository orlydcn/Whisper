package com.orly.whispers.data.repository.popularfeed

import androidx.paging.PagingData
import com.orly.whispers.data.model.PopularWhispers
import kotlinx.coroutines.flow.Flow

interface IPopularFeedRepository {
    fun fetchPopularFeedWhispers(limit: Int): Flow<PagingData<PopularWhispers>>
}