package com.orly.whispers.data.repository.replies

import androidx.paging.PagingData
import com.orly.whispers.data.model.RepliesFeed
import com.orly.whispers.data.model.RepliesWhispers
import com.orly.whispers.data.model.ResultResource
import kotlinx.coroutines.flow.Flow

interface IRepliesRepository {
    fun fetchRepliesWhispers(limit: Int, wid: String): Flow<ResultResource<RepliesFeed>>
}