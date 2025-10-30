package com.example.reusabelpostfeed.di

import android.content.Context
import androidx.room.Room
import com.example.reusabelpostfeed.roomdb.FeedDb
import com.example.reusabelpostfeed.roomdb.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FeeddbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext ctx: Context): FeedDb =
        Room.databaseBuilder(ctx, FeedDb::class.java, "feed.db").build()

    @Provides
    fun providePostDao(db: FeedDb): PostDao = db.getPostDao()
}