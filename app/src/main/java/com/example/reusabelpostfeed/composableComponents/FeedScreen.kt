package com.example.reusabelpostfeed.composableComponents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.reusabelpostfeed.data.FeedConfiguration
import com.example.reusabelpostfeed.viewModels.FeedVMInterface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.reusabelpostfeed.data.PostLayout


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeedScreen(
    config: FeedConfiguration = FeedConfiguration(),
    feedViewModel: FeedVMInterface,
    modifier: Modifier = Modifier
) {
    val state by feedViewModel.uiState.collectAsState()

    val isRefreshing = state.isRefresh
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { if (config.refreshEnabled) feedViewModel.refresh() }
    )

    Box(modifier = modifier.fillMaxSize()) {
        when (config.layout) {
            is PostLayout.ListLayout -> {
                ListFeed(
                    items = state.postItems,
                    config = config,
                    onEndReached = { feedViewModel.loadMore() },
                    onLike = {feedViewModel.likePost(it) },
                    onComment = {id, comment ->  feedViewModel.commentPost(id, comment) },
                    modifier = Modifier
                        .fillMaxSize()
                        .then(if (config.refreshEnabled) Modifier.pullRefresh(pullRefreshState) else Modifier)
                )
            }

            is PostLayout.GridLayout -> {
                GridFeed(
                    items = state.postItems,
                    config = config,
                    onEndReached = { feedViewModel.loadMore() },
                    onLike = { feedViewModel.likePost(it) },
                    onComment = {id, comment -> feedViewModel.commentPost(id, comment) },
                    modifier = Modifier
                        .fillMaxSize()
                        .then(if (config.refreshEnabled) Modifier.pullRefresh(pullRefreshState) else Modifier)
                )
            }
        }

        // Show loading spinner when no data
        if (state.isLoading && state.postItems.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        // Material 2 Pull Refresh Indicator
        if (config.refreshEnabled) {
            PullRefreshIndicator(
                refreshing = isRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        // Error message bar
        state.errorMessage?.let { msg ->
            Surface(modifier = Modifier.align(Alignment.BottomCenter)) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = msg, modifier = Modifier.weight(1f))
                    Button(onClick = { feedViewModel.retryOnFailure() }) {
                        Text("Retry")
                    }
                }
            }
        }

    }

}