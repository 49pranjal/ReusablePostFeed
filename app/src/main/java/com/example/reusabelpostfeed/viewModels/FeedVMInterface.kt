package com.example.reusabelpostfeed.viewModels

import com.example.reusabelpostfeed.data.FeedState
import kotlinx.coroutines.flow.StateFlow

interface FeedVMInterface {
    val uiState: StateFlow<FeedState>


    fun likePost(id: String)
    fun commentPost(id: String, comment: String)
    fun refresh()
    fun loadMore()
    fun retryOnFailure()
}