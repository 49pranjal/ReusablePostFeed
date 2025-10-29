package com.example.reusabelpostfeed.composableComponents

import android.view.ViewGroup
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.reusabelpostfeed.data.FeedConfiguration
import com.example.reusabelpostfeed.data.PostType
import com.example.reusabelpostfeed.utils.ExoPlayerManager
import com.example.reusabelpostfeed.utils.formatTime


@Composable
fun TextPostCard(
    post: PostType.TextPost,
    config: FeedConfiguration,
    onLike: (String) -> Unit,
    onComment: (String, String) -> Unit
) {
    var showComments by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (config.showAvatar) {
                // simplified: replace with row having avatar + name
                Text(text = post.creatorName ?: "Unknown")
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = post.text,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LikeButton(
                    liked = post.isLikedByUser,
                    onClick = { onLike(post.id) },
                    likeCount = post.likesCount
                )
                CommentButton(
                    count = post.commentCounts,
                    onClick = { showComments = true }
                )
                if (config.showTimeStamp) {
                    Spacer(Modifier.weight(1f))
                    Text(text = formatTime(post.createdTime), style = MaterialTheme.typography.caption)
                }
            }
        }
    }

    CommentSheet(
        postId = post.id,
        visible = showComments,
        onDismiss = { showComments = false },
        onSendComment = { id, text -> onComment(id, text) },
        existingComments = post.commentList
    )
}

@Composable
fun ImagePostCard(
    post: PostType.ImagePost,
    config: FeedConfiguration,
    onLike: (String) -> Unit,
    onComment: (String, String) -> Unit
) {

    var showComments by remember { mutableStateOf(false) }

    Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp, shape = RoundedCornerShape(8.dp)) {
        Column {
            // image loader using Coil (you can swap for your image loader)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = post.text,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
            )
            Spacer(Modifier.height(8.dp))
            Column(modifier = Modifier.padding(12.dp)) {
                post.text?.let { Text(it) }
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    LikeButton(
                        liked = post.isLikedByUser,
                        onClick = { onLike(post.id) },
                        likeCount = post.likesCount
                    )
                    CommentButton(
                        count = post.commentCounts,
                        onClick = { showComments = true }
                    )
                    if (config.showTimeStamp) {
                        Spacer(Modifier.weight(1f))
                        Text(text = formatTime(post.createdTime), style = MaterialTheme.typography.caption)
                    }
                }
            }
        }
    }

    CommentSheet(
        postId = post.id,
        visible = showComments,
        onDismiss = { showComments = false },
        onSendComment = { id, text -> onComment(id, text) },
        existingComments = post.commentList
    )
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPostCard(
    post: PostType.VideoPost,
    config: FeedConfiguration,
    onLike: (String) -> Unit,
    onComment: (String, String) -> Unit
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(post.videoUrl))
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
            prepare()
        }
    }

    var showComments by remember { mutableStateOf(false) }
    var visibility by remember { mutableStateOf(0f) }
    var isPlaying by remember { mutableStateOf(false) }

    // Release player on dispose
    DisposableEffect(Unit) {
        onDispose {
            ExoPlayerManager.pause(exoPlayer)
            exoPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (config.isFullScreenPost) Modifier.fillMaxHeight()
                else Modifier.wrapContentHeight()
            )
            .then(
                if (config.autoPlayVideos && config.isFullScreenPost) {
                    Modifier.detectVisibility { percent ->
                        visibility = percent
                        if (percent > 0.5f) {
                            // Auto-play logic only for fullscreen + autoplay mode
                            ExoPlayerManager.play(exoPlayer)
                            isPlaying = true
                        } else {
                            ExoPlayerManager.pause(exoPlayer)
                            isPlaying = false
                        }
                    }
                } else Modifier // no auto-play detection
            )
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(9 / 16f)
                .then(
                    if (config.isFullScreenPost)
                        Modifier.matchParentSize()
                    else Modifier.wrapContentHeight()
                ),
            factory = { ctx ->
                PlayerView(ctx).apply {
                    useController = false // we'll add our own buttons
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    this.player = exoPlayer
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = { it.player = exoPlayer }
        )

        // For manual play/pause (non-autoplay modes)
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.25f))
                .padding(12.dp)
        ) {
            if (!config.autoPlayVideos || !config.isFullScreenPost) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        if (isPlaying) {
                            ExoPlayerManager.pause(exoPlayer)
                            isPlaying = false
                        } else {
                            ExoPlayerManager.play(exoPlayer)
                            isPlaying = true
                        }
                    }) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                Spacer(Modifier.height(4.dp))
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                LikeButton(
                    liked = post.isLikedByUser,
                    onClick = { onLike(post.id) },
                    likeCount = post.likesCount
                )
                CommentButton(
                    count = post.commentCounts,
                    onClick = { showComments = true }
                )
                if (config.showTimeStamp) {
                    Spacer(Modifier.weight(1f))
                    Text(text = formatTime(post.createdTime), style = MaterialTheme.typography.caption)
                }
            }
        }
    }

    CommentSheet(
        postId = post.id,
        visible = showComments,
        onDismiss = { showComments = false },
        onSendComment = { id, text -> onComment(id, text) },
        existingComments = post.commentList
    )
}
