package com.example.reusabelpostfeed.dataSource

import android.content.Context
import com.example.reusabelpostfeed.roomdb.Converter
import com.example.reusabelpostfeed.roomdb.PostEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import javax.inject.Inject

class DataSourceImplementation @Inject constructor(): DataSource {
    override suspend fun readPage(
        context: Context,
        pageIndex: Int
    ): List<PostEntity> = withContext(Dispatchers.IO) {
        val fileName = "postlist_${pageIndex}.json"
        context.assets.open(fileName).use { input ->
            val json = input.bufferedReader().readText()
            val arr = JSONArray(json)
            val converter = Converter()
            buildList {
                for (i in 0 until arr.length()) {
                    val o = arr.getJSONObject(i)
                    val type = o.getString("type")
                    val comments = o.optJSONArray("commentList")?.let { ja ->
                        List(ja.length()) { idx -> ja.optString(idx) }
                    } ?: emptyList()
                    add(
                        PostEntity(
                            id = o.getString("id"),
                            type = type,
                            text = o.optString("text", null),
                            creatorName = o.optString("creatorName", null),
                            createdTime = o.getLong("createdTime"),
                            likesCount = o.getInt("likesCount"),
                            commentCounts = o.getInt("commentCounts"),
                            isLikedByUser = o.getBoolean("isLikedByUser"),
                            imageUrl = o.optString("imageUrl", null),
                            videoUrl = o.optString("videoUrl", null),
                            thumbnailUrl = o.optString("thumbnailUrl", null),
                            commentListJson = converter.fromList(comments)
                        )
                    )
                }
            }
        }
    }
}