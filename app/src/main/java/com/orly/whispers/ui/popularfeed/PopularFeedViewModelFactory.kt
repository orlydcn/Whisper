package com.orly.whispers.ui.popularfeed

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.orly.whispers.data.repository.popularfeed.IPopularFeedRepository

@Suppress("UNCHECKED_CAST")
class PopularFeedViewModelFactory(
    private val repository: IPopularFeedRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularFeedViewModel(repository) as T
    }

}