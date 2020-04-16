package com.kyri.customergeo.api

import com.kyri.customergeo.geolocation.Geolocation
import com.kyri.customergeo.service.CustomerGeolocationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

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