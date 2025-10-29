package com.example.reusabelpostfeed.viewModels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reusabelpostfeed.data.FeedState
import com.example.reusabelpostfeed.data.PostType
import com.example.reusabelpostfeed.useCase.FeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedVMImplementation @Inject constructor(private val feedUseCase: FeedUseCase): ViewModel(), FeedVMInterface {
    private val _uiState = MutableStateFlow(FeedState(isLoading = true))

    override val uiState: StateFlow<FeedState> = _uiState


    init {
        loadInitialFeed()
    }

    private fun loadInitialFeed() {
        viewModelScope.launch {
            try {
                _uiState.value = FeedState(isLoading = true)
                val postItems = feedUseCase()
                _uiState.value = FeedState(postItems = postItems, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = FeedState(isLoading = false, errorMessage = e.message)
            }
        }
    }

    override fun likePost(id: String) {
        Log.d("PostLike",id)
        val current = _uiState.value
        var newLikedState = false
        val updatedList = current.postItems.map { post ->
            if (post.id != id) return@map post
            else newLikedState = !post.isLikedByUser
            when (post) {
                is PostType.TextPost -> {
                    val newLiked = !post.isLikedByUser
                    post.copy(
                        isLikedByUser = newLiked,
                        likesCount = (post.likesCount + if (newLiked) 1 else -1).coerceAtLeast(0)
                    )
                }
                is PostType.ImagePost -> {
                    val newLiked = !post.isLikedByUser
                    post.copy(
                        isLikedByUser = newLiked,
                        likesCount = (post.likesCount + if (newLiked) 1 else -1).coerceAtLeast(0)
                    )
                }
                is PostType.VideoPost -> {
                    val newLiked = !post.isLikedByUser
                    post.copy(
                        isLikedByUser = newLiked,
                        likesCount = (post.likesCount + if (newLiked) 1 else -1).coerceAtLeast(0)
                    )
                }
            }
        }
        _uiState.value = current.copy(postItems = updatedList)

        // 2) Fire-and-forget remote call (reconcile on failure if desired)
        /*viewModelScope.launch {
            try {
                likeRemote(id, newLikedState)
            } catch (e: Exception) {
                // Optional: revert on failure
                revertLike(id)
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }*/
    }

    private fun revertLike(id: String) {
        val current = _uiState.value
        val reverted = current.postItems.map { post ->
            if (post.id != id) return@map post
            when (post) {
                is PostType.TextPost -> {
                    val newLiked = !post.isLikedByUser
                    post.copy(
                        isLikedByUser = newLiked,
                        likesCount = (post.likesCount + if (newLiked) 1 else -1).coerceAtLeast(0)
                    )
                }
                is PostType.ImagePost -> {
                    val newLiked = !post.isLikedByUser
                    post.copy(
                        isLikedByUser = newLiked,
                        likesCount = (post.likesCount + if (newLiked) 1 else -1).coerceAtLeast(0)
                    )
                }
                is PostType.VideoPost -> {
                    val newLiked = !post.isLikedByUser
                    post.copy(
                        isLikedByUser = newLiked,
                        likesCount = (post.likesCount + if (newLiked) 1 else -1).coerceAtLeast(0)
                    )
                }
            }
        }
        _uiState.value = current.copy(postItems = reverted)
    }

    override fun commentPost(id: String, comment: String) {
        //Log.d("commentPost","id - $id, comment - $comment")

        if (comment.isBlank()) return

        // 1) Optimistic UI update: increment commentCounts
        val current = _uiState.value
        val updatedList = current.postItems.map { post ->
            if (post.id != id) return@map post
            when (post) {
                is PostType.TextPost -> post.copy(commentCounts = post.commentCounts + 1, commentList = post.commentList + comment)
                is PostType.ImagePost -> post.copy(commentCounts = post.commentCounts + 1, commentList = post.commentList + comment)
                is PostType.VideoPost -> post.copy(commentCounts = post.commentCounts + 1, commentList = post.commentList + comment)
            }
        }
        _uiState.value = current.copy(postItems = updatedList)

        // 2) Fire-and-forget remote call (reconcile on failure if desired)
        /*viewModelScope.launch {
            try {
                addCommentRemote(id, comment)
            } catch (e: Exception) {
                // Optional: revert on failure
                revertCommentIncrement(id)
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }*/
    }

    private fun revertCommentIncrement(id: String) {
        val current = _uiState.value
        val reverted = current.postItems.map { post ->
            if (post.id != id) return@map post
            when (post) {
                is PostType.TextPost -> {
                    val newList = if (post.commentList.isNotEmpty()) post.commentList.dropLast(1) else post.commentList
                    post.copy(commentCounts = (post.commentCounts - 1).coerceAtLeast(0),
                        commentList = newList)
                }
                is PostType.ImagePost -> {
                    val newList = if (post.commentList.isNotEmpty()) post.commentList.dropLast(1) else post.commentList
                    post.copy(commentCounts = (post.commentCounts - 1).coerceAtLeast(0),
                        commentList = newList)
                }
                is PostType.VideoPost -> {
                    val newList = if (post.commentList.isNotEmpty()) post.commentList.dropLast(1) else post.commentList
                    post.copy(commentCounts = (post.commentCounts - 1).coerceAtLeast(0),
                        commentList = newList)
                }
            }
        }
        _uiState.value = current.copy(postItems = reverted)
    }

    override fun refresh() {
        loadInitialFeed()
    }

    override fun loadMore() {
        TODO("Not yet implemented")
    }

    override fun retryOnFailure() {
        loadInitialFeed()
    }
}