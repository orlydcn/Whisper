package com.orly.whispers.data.repository.popularfeed

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orly.whispers.data.model.PopularWhispers
import com.orly.whispers.data.pagingsource.PopularFeedPagingSource
import com.orly.whispers.data.remote.WhispersApi
import kotlinx.coroutines.flow.Flow

class PopularFeedRepositoryImp(
    private val whispersApi: WhispersApi
) : IPopularFeedRepository {
    override fun fetchPopularFeedWhispers(limit: Int): Flow<PagingData<PopularWhispers>> =
        Pager(
            PagingConfig(limit)
        ) {
            PopularFeedPagingSource(
                whispersApi
            )
        }.flow
}