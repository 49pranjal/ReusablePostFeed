package com.example.reusabelpostfeed.composableComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.reusabelpostfeed.data.FeedConfiguration
import com.example.reusabelpostfeed.data.PostLayout
import com.example.reusabelpostfeed.data.PostType

@Composable
fun ListFeed(
    items: List<PostType>,
    config: FeedConfiguration,
    onEndReached: () -> Unit,
    onLike: (String) -> Unit,
    onComment: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    //For Reel Type Post
    if (config.isFullScreenPost) {
        val pagerState = rememberPagerState(pageCount = { items.size })

        VerticalPager(
            state = pagerState,
            modifier = modifier.fillMaxSize()
        ) { page ->
            val post = items[page]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(config.contentPadding)
            ) {
                PostItem(
                    item = post,
                    config = config,
                    onLike = onLike,
                    onComment = onComment
                )
            }

            // 👇 Trigger pagination when nearing end
            if (page >= items.size - 2) {
                onEndReached()
            }
        }
    } else {
        LazyColumn(
            modifier = modifier.padding(horizontal = config.contentPadding),
            contentPadding = PaddingValues(vertical = config.itemSpacing)
        ) {
            itemsIndexed(items, key = { _, it -> it.id }) { index, item ->
                PostItem(
                    item = item,
                    config = config,
                    onLike = onLike,
                    onComment = onComment
                )
                Spacer(modifier = Modifier.height(config.itemSpacing))

                // Trigger pagination when nearing end
                if (index >= items.size - 3) {
                    onEndReached()
                }
            }
        }
    }
}

@Composable
fun GridFeed(
    items: List<PostType>,
    config: FeedConfiguration,
    onEndReached: () -> Unit,
    onLike: (String) -> Unit,
    onComment: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(config.gridColumns),
        modifier = modifier.padding(horizontal = config.contentPadding),
        contentPadding = PaddingValues(vertical = config.itemSpacing),
        horizontalArrangement = Arrangement.spacedBy(config.itemSpacing),
        verticalArrangement = Arrangement.spacedBy(config.itemSpacing)
    ) {
        itemsIndexed(items, key = { _, it -> it.id }) { index, item ->
            PostItem(
                item = item,
                config = config,
                onLike = onLike,
                onComment = onComment
            )

            if (index >= items.size - 3) {
                onEndReached()
            }
        }
    }
}

@Composable
private fun PostItem(
    item: PostType,
    config: FeedConfiguration,
    onLike: (String) -> Unit,
    onComment: (String, String) -> Unit
) {
    when (item) {
        is PostType.TextPost -> TextPostCard(item, config, onLike, onComment)
        is PostType.ImagePost -> ImagePostCard(item, config, onLike, onComment)
        is PostType.VideoPost -> VideoPostCard(item, config, onLike, onComment)
    }
}
