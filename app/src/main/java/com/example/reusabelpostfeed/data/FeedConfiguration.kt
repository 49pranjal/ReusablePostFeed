package com.example.reusabelpostfeed.data

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class FeedConfiguration(
    val layout: PostLayout = PostLayout.ListLayout,
    val gridColumns: Int = 2,                  // used when layout == Grid
    val refreshEnabled: Boolean = true,
    val showTimeStamp: Boolean = true,
    val showAvatar: Boolean = false,
    val autoPlayVideos: Boolean = false,
    val itemSpacing: Dp = 8.dp,
    val contentPadding: Dp = 12.dp,
    val isFullScreenPost: Boolean = false

)

sealed class PostLayout {
    object ListLayout: PostLayout()
    object GridLayout: PostLayout()
}