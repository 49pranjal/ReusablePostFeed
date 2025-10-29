package com.example.reusabelpostfeed.repository

import com.example.reusabelpostfeed.data.PostType
import javax.inject.Inject

class FeedRepoImplementation @Inject constructor(): FeedRepo {
    override suspend fun getListOfPosts(): List<PostType> {
        /*return listOf(
            PostType.TextPost("1", "Hello world!", "Alice", System.currentTimeMillis()),
            PostType.ImagePost("2", "Check this out!", imageUrl = "https://picsum.photos/400", creatorName = "Bob", createdTime = System.currentTimeMillis()),
            PostType.VideoPost("3", "Watch this video", videoUrl = "https://example.com/video.mp4", creatorName =  "Charlie", createdTime =  System.currentTimeMillis()),
            PostType.TextPost("4", "Hello world!", "Alice", System.currentTimeMillis()),
            PostType.ImagePost("5", "Check this out!", imageUrl = "https://picsum.photos/400", creatorName = "Bob", createdTime = System.currentTimeMillis()),
            PostType.VideoPost("6", "Watch this video", videoUrl = "https://example.com/video.mp4", creatorName =  "Charlie", createdTime =  System.currentTimeMillis()),
            PostType.TextPost("7", "Hello world!", "Alice", System.currentTimeMillis()),
            PostType.ImagePost("8", "Check this out!", imageUrl = "https://picsum.photos/400", creatorName = "Bob", createdTime = System.currentTimeMillis()),
            PostType.VideoPost("9", "Watch this video", videoUrl = "https://example.com/video.mp4", creatorName =  "Charlie", createdTime =  System.currentTimeMillis()),
            PostType.TextPost("10", "Hello world!", "Alice", System.currentTimeMillis()),
            PostType.ImagePost("11", "Check this out!", imageUrl = "https://picsum.photos/400", creatorName = "Bob", createdTime = System.currentTimeMillis()),
            PostType.VideoPost("12", "Watch this video", videoUrl = "https://example.com/video.mp4", creatorName =  "Charlie", createdTime =  System.currentTimeMillis()),
            PostType.TextPost("13", "Hello world!", "Alice", System.currentTimeMillis()),
            PostType.ImagePost("14", "Check this out!", imageUrl = "https://picsum.photos/400", creatorName = "Bob", createdTime = System.currentTimeMillis()),
            PostType.VideoPost("15", "Watch this video", videoUrl = "https://example.com/video.mp4", creatorName =  "Charlie", createdTime =  System.currentTimeMillis()),
            PostType.TextPost("16", "Hello world!", "Alice", System.currentTimeMillis()),
            PostType.ImagePost("17", "Check this out!", imageUrl = "https://picsum.photos/400", creatorName = "Bob", createdTime = System.currentTimeMillis()),
            PostType.VideoPost("18", "Watch this video", videoUrl = "https://example.com/video.mp4", creatorName =  "Charlie", createdTime =  System.currentTimeMillis())



        )*/

        return listOf(
            PostType.VideoPost(
                id = "v1",
                text = "Big Buck Bunny sample.",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                creatorName = "Nina",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 15,
                likesCount = 220,
                commentCounts = 40,
                isLikedByUser = true,
                commentList = listOf("Trial", "Error")
            ),
            PostType.ImagePost(
                id = "i6",
                text = "Calm lake reflections.",
                imageUrl = "https://picsum.photos/1200/800?grayscale",
                creatorName = "Lia",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 330,
                likesCount = 76,
                commentCounts = 8,
                isLikedByUser = false,
                commentList = listOf("Trial", "Error")
            ),
            PostType.TextPost(
                id = "t4",
                text = "Using sealed classes to model UI states keeps things safe and readable.",
                creatorName = "Diana",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 360,
                likesCount = 67,
                commentCounts = 14,
                isLikedByUser = true,
                commentList = listOf("Trial", "Error","text")
            ),
            PostType.VideoPost(
                id = "v2",
                text = "Sintel short film.",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4",
                creatorName = "Omar",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 75,
                likesCount = 180,
                commentCounts = 27,
                isLikedByUser = false,
                commentList = listOf("Trial", "Error","Video")
            ),
            PostType.ImagePost(
                id = "i2",
                text = "Street photography shot.",
                imageUrl = "https://picsum.photos/id/33/900/600",
                creatorName = "Hana",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 90,
                likesCount = 98,
                commentCounts = 7,
                isLikedByUser = false,
                commentList = listOf("Trial", "Error")
            ),
            PostType.TextPost(
                id = "t6",
                text = "Caching + pagination boosted scroll performance significantly.",
                creatorName = "Fiona",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 700,
                likesCount = 54,
                commentCounts = 11,
                isLikedByUser = true
            ),
            PostType.VideoPost(
                id = "v3",
                text = "Tears of Steel scene.",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4",
                creatorName = "Pia",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 135,
                likesCount = 156,
                commentCounts = 21,
                isLikedByUser = false,
                commentList = listOf("Trial", "Error","Video")
            ),
            PostType.ImagePost(
                id = "i5",
                text = "City lights at night.",
                imageUrl = "https://picsum.photos/id/1025/960/640",
                creatorName = "Ken",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 270,
                likesCount = 87,
                commentCounts = 10,
                isLikedByUser = false
            ),
            PostType.TextPost(
                id = "t2",
                text = "Kotlin tip: prefer data classes for immutable models.",
                creatorName = "Bob",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 120,
                likesCount = 45,
                commentCounts = 9,
                isLikedByUser = true,
                commentList = listOf("Trial", "Error")
            ),
            PostType.VideoPost(
                id = "v4",
                text = "Elephants Dream clip.",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
                creatorName = "Quinn",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 195,
                likesCount = 132,
                commentCounts = 18,
                isLikedByUser = true
            ),
            PostType.ImagePost(
                id = "i7",
                text = "Coffee and code.",
                imageUrl = "https://picsum.photos/900/600?blur=2",
                creatorName = "Max",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 390,
                likesCount = 111,
                commentCounts = 15,
                isLikedByUser = true,
                commentList = listOf("Trial", "Error","Image")
            ),
            PostType.TextPost(
                id = "t5",
                text = "REST API integration wrapped with repository pattern for clean boundaries.",
                creatorName = "Evan",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 520,
                likesCount = 31,
                commentCounts = 6,
                isLikedByUser = false
            ),
            PostType.VideoPost(
                id = "v5",
                text = "Subaru Outback on street and dirt.",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
                creatorName = "Ria",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 255,
                likesCount = 77,
                commentCounts = 9,
                isLikedByUser = false
            ),
            PostType.ImagePost(
                id = "i1",
                text = "Sunset vibes.",
                imageUrl = "https://picsum.photos/id/10/800/600",
                creatorName = "Greg",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 30,
                likesCount = 120,
                commentCounts = 18,
                isLikedByUser = true,
                commentList = listOf("Trial", "Error")
            ),
            PostType.TextPost(
                id = "t3",
                text = "Today’s standup went great. Shipping a new feature soon!",
                creatorName = "Charlie",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 240,
                likesCount = 23,
                commentCounts = 2,
                isLikedByUser = false
            ),
            PostType.VideoPost(
                id = "v6",
                text = "For Bigger Joyrides.",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
                creatorName = "Sam",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 315,
                likesCount = 84,
                commentCounts = 12,
                isLikedByUser = false
            ),
            PostType.ImagePost(
                id = "i3",
                text = "Minimal workspace setup.",
                imageUrl = "https://picsum.photos/id/77/1080/720",
                creatorName = "Ivan",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 150,
                likesCount = 64,
                commentCounts = 5,
                isLikedByUser = false
            ),
            PostType.TextPost(
                id = "t1",
                text = "Hello world! First post from the feed.",
                creatorName = "Alice",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 60,
                likesCount = 12,
                commentCounts = 3,
                isLikedByUser = false
            ),
            PostType.VideoPost(
                id = "v7",
                text = "What car can you get for a grand?",
                videoUrl = "https://storage.googleapis.com/gtv-videos-bucket/sample/WhatCarCanYouGetForAGrand.mp4",
                creatorName = "Tara",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 375,
                likesCount = 91,
                commentCounts = 13,
                isLikedByUser = true,
                commentList = listOf("Trial", "Error","Video")            ),
            PostType.ImagePost(
                id = "i4",
                text = "Weekend hiking trail.",
                imageUrl = "https://picsum.photos/id/237/1000/667",
                creatorName = "Jade",
                createdTime = System.currentTimeMillis() - 1000L * 60 * 210,
                likesCount = 143,
                commentCounts = 22,
                isLikedByUser = true
            )
        )
    }

    override suspend fun likedPost(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun commentedPost(id: String, comment: String) {
        TODO("Not yet implemented")
    }
}