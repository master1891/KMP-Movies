package com.nels.master.kmptutorial1

import com.nels.master.kmptutorial1.data.RegionDatasource
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreLocation.CLGeocoder
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.CLPlacemark
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume

class IOsRegionDataSource : RegionDatasource {

    override suspend fun fetchRegion(): String {
        return getCurrentlocation()?.toRegion() ?: "US"
    }

    private suspend fun getCurrentlocation(): CLLocation? {


        return suspendCancellableCoroutine { continuation ->

            val locationManager = CLLocationManager()

            locationManager.delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
                override fun locationManager(
                    manager: CLLocationManager,
                    didUpdateLocations: List<*>
                ) {

                    val location = didUpdateLocations.firstOrNull() as? CLLocation
                    locationManager.stopUpdatingLocation()
                    continuation.resume(location)
                }

                override fun locationManager(
                    manager: CLLocationManager,
                    didFailWithError: NSError
                ) {
                    continuation.resume(null)
                }
            }

            locationManager.requestWhenInUseAuthorization()
            locationManager.startUpdatingLocation()
        }

    }

    private suspend fun CLLocation.toRegion(): String? {


        return suspendCancellableCoroutine { continuation ->
            val geocoder = CLGeocoder()

            geocoder.reverseGeocodeLocation(this, completionHandler = { placemarks, error ->
                )
                if (error != null) {
                    continuation.resume(null)
                } else {
                    val countryCode = (placemarks?.firstOrNull() as? CLPlacemark)?.ISOcountryCode
                    continuation.resume(countryCode)
                }
            })
        }
    }

}


