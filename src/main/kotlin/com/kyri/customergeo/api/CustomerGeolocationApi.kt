package com.kyri.customergeo.api

import com.kyri.customergeo.geolocation.Geolocation
import com.kyri.customergeo.service.CustomerGeolocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * Inbound API adapter for customer geolocations. It delegates to the customerGeolocationService interface (port),
 * to allow the internal business logic of the application to take over.
 */
@RestController
class CustomerGeolocationApi(
    private val customerGeolocationService: CustomerGeolocationService
) {
    @GetMapping("/customer/{customerId}/geolocation")
    fun getGeolocationForCustomer(@PathVariable customerId: Int): ResponseEntity<Geolocation> {
        val geolocation = customerGeolocationService.geolocationForCustomer(customerId)
        return ResponseEntity.ok(geolocation)
    }
}