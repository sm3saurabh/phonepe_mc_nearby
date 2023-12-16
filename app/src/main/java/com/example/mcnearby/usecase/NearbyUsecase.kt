package com.example.mcnearby.usecase

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.mcnearby.NearbyLocationProvider
import com.example.mcnearby.database.entity.VenueEntity
import com.example.mcnearby.repo.NearbyRepo

@OptIn(ExperimentalPagingApi::class)
class NearbyUseCase(
    private val nearbyRepo: NearbyRepo,
    private val nearbyLocationProvider: NearbyLocationProvider,
    private val queryProvider: QueryProvider
): RemoteMediator<Int, VenueEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VenueEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.PREPEND -> {
                return MediatorResult.Success(true)
            }
            LoadType.REFRESH -> {
                1
            }
            LoadType.APPEND -> (state.lastItemOrNull()?.page ?: 0) + 1
        }

        val location = nearbyLocationProvider.getDeviceLocation()

        val venues = nearbyRepo.fetchNearbyVenuesFromServer(
            page,
            location.lat,
            location.lon,
            queryProvider.getUserQuery()
        )

        if (venues.isEmpty()) {
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        nearbyRepo.saveVenuesToLocal(venues)

        return MediatorResult.Success(endOfPaginationReached = false)
    }
}

interface QueryProvider {
    fun getUserQuery(): String?
}