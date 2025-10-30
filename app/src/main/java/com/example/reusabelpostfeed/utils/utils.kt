package com.example.reusabelpostfeed.utils

import com.example.reusabelpostfeed.data.PostType
import com.example.reusabelpostfeed.roomdb.Converter
import com.example.reusabelpostfeed.roomdb.PostEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTime(epochMs: Long): String {
    val sdf = SimpleDateFormat("dd MMM • HH:mm", Locale.getDefault())
    return sdf.format(Date(epochMs))
}

//Mappers from Entity to Domain and Vice-Versa
fun PostEntity.toDomain(): PostType = when (type) {
    "text" -> PostType.TextPost(
        id,
        text.orEmpty(),
        creatorName.orEmpty(),
        createdTime,
        likesCount,
        commentCounts,
        isLikedByUser,
        Converter().toList(commentListJson)
    )

    "image" -> PostType.ImagePost(
        id,
        text,
        creatorName,
        createdTime,
        imageUrl ?: "",
        thumbnailUrl,
        likesCount,
        commentCounts,
        isLikedByUser,
        Converter().toList(commentListJson)
    )

    "video" -> PostType.VideoPost(
        id,
        text,
        creatorName,
        createdTime,
        videoUrl ?: "",
        thumbnailUrl,
        likesCount,
        commentCounts,
        isLikedByUser,
        Converter().toList(commentListJson)
    )

    else -> PostType.TextPost(
        id,
        text.orEmpty(),
        creatorName.orEmpty(),
        createdTime,
        likesCount,
        commentCounts,
        isLikedByUser,
        Converter().toList(commentListJson)
    )
}

fun PostType.toEntity(commentList: List<String>): PostEntity = when (this) {
    is PostType.TextPost ->
        PostEntity(
            id,
            "text",
            text,
            creatorName,
            createdTime,
            likesCount,
            commentCounts,
            isLikedByUser,
            null,
            null,
            null,
            Converter().fromList(commentList)
        )

    is PostType.ImagePost ->
        PostEntity(
            id,
            "image",
            text,
            creatorName,
            createdTime,
            likesCount,
            commentCounts,
            isLikedByUser,
            imageUrl,
            null,
            thumbnailUrl,
            Converter().fromList(commentList)
        )

    is PostType.VideoPost ->
        PostEntity(
            id,
            "video",
            text,
            creatorName,
            createdTime,
            likesCount,
            commentCounts,
            isLikedByUser,
            null,
            videoUrl,
            thumbnailUrl,
            Converter().fromList(commentList)
        )
}