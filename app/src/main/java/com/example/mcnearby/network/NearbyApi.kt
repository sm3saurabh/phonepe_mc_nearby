package com.example.mcnearby.network

import com.example.mcnearby.network.model.NearbyApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NearbyApi {

    // No need for interceptor for a single api, we'll pass the client id directly
    @GET("2/venues")
    suspend fun getNearbyPlaces(
        @Query("client_id") clientId: String = "Mzg0OTc0Njl8MTcwMDgxMTg5NC44MDk2NjY5",
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("range") range: String,
        @Query("q") query: String? = null
    ): Response<NearbyApiResponse>
}