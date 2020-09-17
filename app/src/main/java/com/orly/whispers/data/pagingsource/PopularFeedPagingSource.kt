package com.orly.whispers.data.pagingsource

import androidx.paging.PagingSource
import com.orly.whispers.data.model.PopularWhispers
import com.orly.whispers.data.remote.WhispersApi
import retrofit2.HttpException
import java.io.IOException

class PopularFeedPagingSource(
    private val whispersApi: WhispersApi
) : PagingSource<String, PopularWhispers>() {
    override suspend fun load(params: LoadParams<String>): LoadResult<String, PopularWhispers> {
        return try {
            val data = whispersApi.fetchFeed(
                scrollId = params.key,
                limit = params.loadSize
            )
            LoadResult.Page(
                data = data.popular,
                prevKey = null,
                nextKey = data.scroll_id
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}