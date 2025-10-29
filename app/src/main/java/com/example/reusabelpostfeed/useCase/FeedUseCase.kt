package com.example.reusabelpostfeed.useCase

import com.example.reusabelpostfeed.data.PostType
import com.example.reusabelpostfeed.repository.FeedRepo
import javax.inject.Inject

class FeedUseCase @Inject constructor(private val repo: FeedRepo) {
    suspend operator fun invoke(): List<PostType> = repo.getListOfPosts()
}