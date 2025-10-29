package com.example.reusabelpostfeed.di

import androidx.compose.ui.tooling.preview.Preview
import com.example.reusabelpostfeed.repository.FeedRepo
import com.example.reusabelpostfeed.repository.FeedRepoImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedModule {
    
    @Provides
    @Singleton
    fun getFeedRepo(): FeedRepo = FeedRepoImplementation()
}