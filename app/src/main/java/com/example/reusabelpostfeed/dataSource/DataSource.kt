package com.example.reusabelpostfeed.dataSource

import android.content.Context
import com.example.reusabelpostfeed.roomdb.PostEntity

interface DataSource {
    suspend fun readPage(context: Context, pageIndex: Int): List<PostEntity>
}