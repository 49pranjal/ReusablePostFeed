package com.example.reusabelpostfeed.repository

import com.example.reusabelpostfeed.data.PostType

interface FeedRepo {
    suspend fun getListOfPosts(): List<PostType>
    suspend fun likedPost(id: String)
    suspend fun commentedPost(id: String, comment: String)
}