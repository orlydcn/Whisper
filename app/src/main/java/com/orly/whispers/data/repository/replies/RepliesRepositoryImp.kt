package com.orly.whispers.data.repository.replies

import com.orly.whispers.data.model.RepliesFeed
import com.orly.whispers.data.model.ResultResource
import com.orly.whispers.data.remote.BaseDataSource
import com.orly.whispers.data.remote.WhispersApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepliesRepositoryImp(
    private val whispersApi: WhispersApi
) : IRepliesRepository {
    override fun fetchRepliesWhispers(
        limit: Int,
        wid: String
    ): Flow<ResultResource<RepliesFeed>> = flow {
        emit(
            object : BaseDataSource() {
                suspend fun fetchData() = getResult {
                    whispersApi.fetchReplies(
                        wid, limit
                    )
                }
            }.fetchData()
        )
    }
}