package com.orly.whispers.ui.replies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orly.whispers.data.model.RepliesFeed
import com.orly.whispers.data.model.ResultResource
import com.orly.whispers.data.repository.replies.IRepliesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RepliesViewModel(
    private val repliesRepository: IRepliesRepository,
    private val wid: String
) : ViewModel() {

    private val _repliesLiveData = MutableLiveData<ResultResource<RepliesFeed>>()

    val repliesLiveData: LiveData<ResultResource<RepliesFeed>>
        get() = _repliesLiveData

    fun getReplies() =
        viewModelScope.launch {
            repliesRepository
                .fetchRepliesWhispers(LIMIT_REPLIES, wid)
                .collect {
                    _repliesLiveData.value = it
                }
        }


    companion object {
        private const val LIMIT_REPLIES = 200
    }
}