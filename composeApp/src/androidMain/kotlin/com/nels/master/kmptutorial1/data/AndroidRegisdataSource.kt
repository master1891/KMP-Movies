package com.nels.master.kmptutorial1.data

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class AndroidRegisdataSource(
    private val geocoder: Geocoder,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : RegionDatasource {

    override suspend fun fetchRegion(): String {
        return fusedLocationProviderClient.lastLocation()?.toRegion()?:"US"
    }

    @SuppressLint("MissingPermission")
    private suspend fun FusedLocationProviderClient.lastLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener { exception ->
                continuation.resume(null)
            }
        }
    }

    fun Location.toRegion(): String? {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return addresses?.firstOrNull()?.countryCode
    }
}

