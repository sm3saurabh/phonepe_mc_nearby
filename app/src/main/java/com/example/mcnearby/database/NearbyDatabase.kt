package com.example.mcnearby.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mcnearby.database.dao.VenueDao
import com.example.mcnearby.database.entity.VenueEntity

@Database(entities = [VenueEntity::class], version = 1)
abstract class NearbyDatabase: RoomDatabase() {
    abstract fun venueDao(): VenueDao
}