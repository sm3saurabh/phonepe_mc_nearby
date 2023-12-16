package com.example.mcnearby.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("venue_entity")
data class VenueEntity(
    val venueName: String,
    val venueAddress: String,
    @PrimaryKey val venueId: Int,
    val page: Int
)
