package com.orly.whispers.data.remote

import com.orly.whispers.data.model.PopularFeed
import com.orly.whispers.data.model.RepliesFeed
import com.orly.whispers.data.model.RepliesWhispers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WhispersApi {

    @GET(ApiConstant.POPULAR_FEED)
    suspend fun fetchFeed(
        @Query("scroll_id") scrollId: String? = null,
        @Query("limit") limit: Int = 30
    ): PopularFeed

    @GET(ApiConstant.REPLIES_FEED)
    suspend fun fetchReplies(
        @Query("wid") wid: String,
        @Query("limit") limit: Int = 30
    ): Response<RepliesFeed>

    companion object {
        fun create(): WhispersApi {
            return Retrofit
                .Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .client(HttpClientProvider.httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WhispersApi::class.java)
        }
    }
}