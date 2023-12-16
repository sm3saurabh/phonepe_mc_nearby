package com.example.mcnearby

import android.annotation.SuppressLint
import android.content.Context
import com.example.mcnearby.network.model.Location
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.suspendCoroutine

interface NearbyLocationProvider {
    suspend fun getDeviceLocation(): Location
}

class NearbyLocationProviderImpl(
    private val context: Context
): NearbyLocationProvider {

    @SuppressLint("MissingPermission")
    override suspend fun getDeviceLocation(): Location {
        return suspendCancellableCoroutine {

            LocationServices.getFusedLocationProviderClient(context)
                .lastLocation
                .addOnSuccessListener { location ->
                     it.resumeWith(Result.success(Location(location.latitude, location.longitude)))
                }
        }
    }

}

