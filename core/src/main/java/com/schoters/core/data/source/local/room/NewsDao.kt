package com.schoters.core.data.source.local.room

import androidx.room.*
import com.schoters.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM SavedNews")
    fun getSavedNewsFromDB(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSavedNews(news: NewsEntity)

    @Delete
    fun deleteSavedNews(news: NewsEntity)

}