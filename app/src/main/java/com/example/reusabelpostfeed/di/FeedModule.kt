package com.example.reusabelpostfeed.di

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import com.example.reusabelpostfeed.dataSource.DataSource
import com.example.reusabelpostfeed.dataSource.DataSourceImplementation
import com.example.reusabelpostfeed.repository.FeedRepo
import com.example.reusabelpostfeed.repository.FeedRepoImplementation
import com.example.reusabelpostfeed.roomdb.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeedModule {

    @Provides
    @Singleton
    fun provideDataSource(): DataSource =
        DataSourceImplementation() // or inject context into the impl’s @Inject constructor

    @Provides
    @Singleton
    fun getFeedRepo(
        dao: PostDao,
        dataSource: DataSource,
        @ApplicationContext context: Context
    ): FeedRepo = FeedRepoImplementation(dao, dataSource, context)
}