package com.orly.whispers.util

import android.content.Context
import com.orly.whispers.data.remote.WhispersApi
import com.orly.whispers.data.repository.popularfeed.IPopularFeedRepository
import com.orly.whispers.data.repository.popularfeed.PopularFeedRepositoryImp
import com.orly.whispers.data.repository.replies.IRepliesRepository
import com.orly.whispers.data.repository.replies.RepliesRepositoryImp

interface ServiceLocator {
    companion object {
        private val LOCK = Any()
        private var instance: ServiceLocator? = null
        fun instance(): ServiceLocator {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = DefaultServiceLocator()
                }
                return instance!!
            }
        }
    }

    fun getPopularFeedRepository(): IPopularFeedRepository

    fun getRepliesRepository(): IRepliesRepository

    fun getWhispersApi(): WhispersApi

    open class DefaultServiceLocator : ServiceLocator {
        private val api by lazy {
            WhispersApi.create()
        }

        override fun getPopularFeedRepository(): IPopularFeedRepository =
            PopularFeedRepositoryImp(api)

        override fun getRepliesRepository(): IRepliesRepository =
            RepliesRepositoryImp(api)

        override fun getWhispersApi(): WhispersApi = api
    }
}