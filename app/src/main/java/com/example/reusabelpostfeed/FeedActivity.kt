package com.example.reusabelpostfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.reusabelpostfeed.composableComponents.FeedScreen
import com.example.reusabelpostfeed.data.FeedConfiguration
import com.example.reusabelpostfeed.data.PostLayout
import com.example.reusabelpostfeed.viewModels.FeedVMImplementation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeedActivity : ComponentActivity() {

    private val feedViewModel: FeedVMImplementation by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val configType = intent.getStringExtra(getString(R.string.config_type))

        val config = when(configType) {
            getString(R.string.compactlistfeed) -> {
                FeedConfiguration(
                    layout = PostLayout.ListLayout,
                    showAvatar = true,
                    itemSpacing = 4.dp,
                    contentPadding = 4.dp,
                    autoPlayVideos = false
                )
            }
            getString(R.string.gridfeed) -> {
                FeedConfiguration(
                    layout = PostLayout.GridLayout,
                    gridColumns = 2,
                    showAvatar = false,
                    autoPlayVideos = false,
                    showTimeStamp = false
                )
            }

            else -> {
                FeedConfiguration(
                    layout = PostLayout.ListLayout,
                    showAvatar = false,
                    isFullScreenPost = true,
                    showTimeStamp = false,
                    autoPlayVideos = true
                )

            }
        }

        setContent {
            MaterialTheme {
                FeedScreen(
                    config,
                    feedViewModel
                )
            }
        }
    }
}