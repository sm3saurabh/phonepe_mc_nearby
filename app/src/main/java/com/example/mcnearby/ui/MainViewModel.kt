package com.example.mcnearby.ui

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.mcnearby.NearbyLocationProvider
import com.example.mcnearby.repo.NearbyRepo
import com.example.mcnearby.usecase.NearbyUseCase
import com.example.mcnearby.usecase.QueryProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: NearbyRepo,
    nearbyLocationProvider: NearbyLocationProvider
) : ViewModel() {

    private val userQuery: String? = null

    val pager = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        remoteMediator = NearbyUseCase(repo, nearbyLocationProvider, object : QueryProvider {
            override fun getUserQuery(): String? {
                return userQuery
            }

        }),
        initialKey = 1
    ) {
        repo.getAllVenues()
    }

}