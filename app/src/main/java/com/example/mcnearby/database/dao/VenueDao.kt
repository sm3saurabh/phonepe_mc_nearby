package com.example.mcnearby.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mcnearby.database.entity.VenueEntity

@Dao
interface VenueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(venues: List<VenueEntity>)

    @Query("SELECT * FROM venue_entity")
    fun pagingSource(): PagingSource<Int, VenueEntity>

    @Query("DELETE FROM venue_entity")
    suspend fun clear()
}