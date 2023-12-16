package com.example.mcnearby.mapper

import com.example.mcnearby.database.entity.VenueEntity
import com.example.mcnearby.network.model.NearbyApiResponse

fun NearbyApiResponse.toVenueEntity(): List<VenueEntity> {
    return this.venues.map {
        VenueEntity(
            venueName = it.name,
            venueAddress = it.extendedAddress,
            venueId = it.id
        )
    }
}