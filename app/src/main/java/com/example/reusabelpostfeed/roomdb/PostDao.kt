package com.example.reusabelpostfeed.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {
    @Query("SELECT * FROM Posts ORDER BY createdTime DESC LIMIT :limit OFFSET :offset")
    suspend fun getPage(limit: Int, offset: Int): List<PostEntity>

    @Query("SELECT COUNT(*) FROM Posts")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(items: List<PostEntity>)

    @Query("SELECT * FROM posts WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): PostEntity?

    @Query("UPDATE posts SET isLikedByUser = :liked, likesCount = :likes WHERE id = :id")
    suspend fun updateLike(id: String, liked: Boolean, likes: Int)

    @Query("UPDATE posts SET commentCounts = :comments, commentListJson = :commentListJson WHERE id = :id")
    suspend fun updateComments(id: String, comments: Int, commentListJson: String)
}