package com.example.reusabelpostfeed.data

sealed class PostType {
    abstract val id: String
    abstract val text: String?
    abstract val creatorName: String?
    abstract val createdTime: Long
    abstract val likesCount: Int
    abstract val commentCounts: Int
    abstract val commentList: List<String>
    abstract val isLikedByUser: Boolean

    data class TextPost(
        override val id: String,
        override val text: String,
        override val creatorName: String,
        override val createdTime: Long,
        override val likesCount: Int,
        override val commentCounts: Int,
        override val isLikedByUser: Boolean,
        override val commentList: List<String> = emptyList()
    ): PostType()

    data class ImagePost(
        override val id: String,
        override val text: String? = null,
        override val creatorName: String? = null,
        override val createdTime: Long,
        val imageUrl: String,
        val thumbnailUrl: String? = null,
        override val likesCount: Int,
        override val commentCounts: Int,
        override val isLikedByUser: Boolean,
        override val commentList: List<String> = emptyList()
    ): PostType()

    data class VideoPost(
        override val id: String,
        override val text: String? = null,
        override val creatorName: String? = null,
        override val createdTime: Long,
        val videoUrl: String,
        val thumbnailUrl: String? = null,
        override val likesCount: Int,
        override val commentCounts: Int,
        override val isLikedByUser: Boolean,
        override val commentList: List<String> = emptyList()
    ): PostType()
}