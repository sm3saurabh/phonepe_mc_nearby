package com.example.mcnearby.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.paging.PagingSource
import com.example.mcnearby.database.dao.VenueDao
import com.example.mcnearby.database.entity.VenueEntity
import com.example.mcnearby.mapper.toVenueEntity
import com.example.mcnearby.network.NearbyApi
import com.example.mcnearby.network.model.NearbyApiResponse
import com.example.mcnearby.network.model.Venue
import retrofit2.Response

interface NearbyRepo {
    suspend fun fetchNearbyVenuesFromServer(
        page: Int,
        lat: Double,
        long: Double,
        query: String?
    ): List<VenueEntity>

    suspend fun saveVenuesToLocal(venues: List<VenueEntity>)

    fun getAllVenues(): PagingSource<Int, VenueEntity>
}

class NearbyRepoImpl(
    private val nearbyApi: NearbyApi,
    private val venueDao: VenueDao,
    context: Context
): NearbyRepo {

    private val rangeStore = context.getSharedPreferences("pref_range", Context.MODE_PRIVATE)

    override suspend fun fetchNearbyVenuesFromServer(
        page: Int,
        lat: Double,
        long: Double,
        query: String?
    ): List<VenueEntity> {
        val response =  nearbyApi.getNearbyPlaces(
            page = page,
            lat = lat,
            lon = long,
            range = getSelectedRange(),
            query = query
        )

        return if (response.isSuccessful) {
            response.body()?.toVenueEntity() ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun saveVenuesToLocal(venues: List<VenueEntity>) {
        venueDao.insertAll(venues)
    }

    override fun getAllVenues(): PagingSource<Int, VenueEntity> {
        return venueDao.pagingSource()
    }

    private fun getSelectedRange(): String {
        return rangeStore.getString(KEY_RANGE, DEFAULT_RANGE) ?: DEFAULT_RANGE
    }

    companion object {
        const val KEY_RANGE = "key_range"
        const val DEFAULT_RANGE = "3mi"
    }


}