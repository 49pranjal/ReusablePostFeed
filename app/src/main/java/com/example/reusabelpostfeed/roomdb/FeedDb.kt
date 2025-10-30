package com.example.reusabelpostfeed.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class FeedDb: RoomDatabase() {

    abstract fun getPostDao(): PostDao
}