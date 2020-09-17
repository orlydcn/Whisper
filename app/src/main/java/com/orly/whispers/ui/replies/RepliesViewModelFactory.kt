package com.orly.whispers.ui.replies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.orly.whispers.data.repository.replies.IRepliesRepository

@Suppress("UNCHECKED_CAST")
class RepliesViewModelFactory(
    private val repository: IRepliesRepository,
    private val wid: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepliesViewModel(repository, wid) as T
    }
}