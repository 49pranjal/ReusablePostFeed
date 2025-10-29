package com.example.reusabelpostfeed.composableComponents

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun LikeButton(
    liked: Boolean,
    onClick: () -> Unit,
    likeCount: Int
) {
    val targetScale = if (liked) 1.2f else 1f
    val scale by animateFloatAsState(targetValue = targetScale, animationSpec = tween(180), label = "like-scale")
    val tint by animateColorAsState(
        targetValue = if (liked) Color(0xFFE91E63) else LocalContentColor.current.copy(alpha = ContentAlpha.medium),
        animationSpec = tween(180), label = "like-color"
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = if (liked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale)
            )
        }
        Text(text = likeCount.toString(), style = MaterialTheme.typography.body2, modifier = Modifier.padding(end = 8.dp))
    }
}

@Composable
fun CommentButton(
    count: Int,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = onClick) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Comment, contentDescription = null)
        }
        Text(text = count.toString(), style = MaterialTheme.typography.body2)
    }
}