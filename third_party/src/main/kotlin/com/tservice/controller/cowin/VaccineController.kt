package com.tservice.controller.vaccine

import com.tservice.controller.vaccine.dao.Hospital
import com.tservice.controller.vaccine.service.CowinService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vaccine")
class VaccineController(
    val cowinService: CowinService
) {

    @GetMapping("/pincode/{pin_code}")
    fun isThereIsAnyAvailableSlot(
        @PathVariable pin_code: String?,
        @RequestParam ageLimit: Int?
    ): List<Hospital> {
        return cowinService.getValue(pin_code ?: "248001", ageLimit ?: 18)
    }

}