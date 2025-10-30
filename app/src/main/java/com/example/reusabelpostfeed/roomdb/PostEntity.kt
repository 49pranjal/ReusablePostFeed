package com.example.reusabelpostfeed.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Posts")
data class PostEntity (
    @PrimaryKey val id: String,
    val type: String, // "text" | "image" | "video"
    val text: String?,
    val creatorName: String?,
    val createdTime: Long,
    val likesCount: Int,
    val commentCounts: Int,
    val isLikedByUser: Boolean,
    val imageUrl: String?,
    val videoUrl: String?,
    val thumbnailUrl: String?,
    val commentListJson: String
)