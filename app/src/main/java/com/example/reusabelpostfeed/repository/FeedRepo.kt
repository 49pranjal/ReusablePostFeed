package com.example.reusabelpostfeed.repository

import com.example.reusabelpostfeed.data.PostType

interface FeedRepo {
    suspend fun getListOfPosts(pageNo: Int, pageSize: Int): List<PostType>
    suspend fun likedPost(id: String): PostType?
    suspend fun commentedPost(id: String, comment: String): PostType?
}