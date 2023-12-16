package com.example.mcnearby.network.model
import com.squareup.moshi.JsonClass

import com.squareup.moshi.Json


@JsonClass(generateAdapter = true)
data class NearbyApiResponse(
    @Json(name = "meta")
    val meta: Meta,
    @Json(name = "venues")
    val venues: List<Venue>
)

@JsonClass(generateAdapter = true)
data class Meta(
    @Json(name = "geolocation")
    val geolocation: Geolocation,
    @Json(name = "page")
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "took")
    val took: Int,
    @Json(name = "total")
    val total: Int
)

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "access_method")
    val accessMethod: Any,
    @Json(name = "address")
    val address: String,
    @Json(name = "capacity")
    val capacity: Int,
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "display_location")
    val displayLocation: String,
    @Json(name = "extended_address")
    val extendedAddress: String,
    @Json(name = "has_upcoming_events")
    val hasUpcomingEvents: Boolean,
    @Json(name = "id")
    val id: Int,
    @Json(name = "links")
    val links: List<Any>,
    @Json(name = "location")
    val location: Location,
    @Json(name = "metro_code")
    val metroCode: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "name_v2")
    val nameV2: String,
    @Json(name = "num_upcoming_events")
    val numUpcomingEvents: Int,
    @Json(name = "popularity")
    val popularity: Int,
    @Json(name = "postal_code")
    val postalCode: String,
    @Json(name = "score")
    val score: Int,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "state")
    val state: Any,
    @Json(name = "stats")
    val stats: Stats,
    @Json(name = "timezone")
    val timezone: String,
    @Json(name = "url")
    val url: String
)

@JsonClass(generateAdapter = true)
data class Geolocation(
    @Json(name = "city")
    val city: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "display_name")
    val displayName: String,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "metro_code")
    val metroCode: Any,
    @Json(name = "postal_code")
    val postalCode: String,
    @Json(name = "range")
    val range: String,
    @Json(name = "state")
    val state: String
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double
)

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "event_count")
    val eventCount: Int
)