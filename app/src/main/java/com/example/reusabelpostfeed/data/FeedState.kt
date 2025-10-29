package com.example.reusabelpostfeed.data

data class FeedState(
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val postItems: List<PostType> = emptyList(),
    val errorMessage: String? = null
)
