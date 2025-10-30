package com.example.reusabelpostfeed.useCase

import com.example.reusabelpostfeed.data.PostType
import com.example.reusabelpostfeed.repository.FeedRepo
import javax.inject.Inject

class FeedUseCase @Inject constructor(private val repo: FeedRepo) {
    suspend fun pageLoad(page: Int, pageSize: Int = 6): List<PostType> = repo.getListOfPosts(page, pageSize)
    suspend fun onLikeClick(id: String): PostType? = repo.likedPost(id)
    suspend fun onComment(id: String, text: String): PostType? = repo.commentedPost(id, text)
}