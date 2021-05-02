package com.tservice.controller.vaccine.client

import com.tservice.controller.vaccine.COWIN_URL
import com.tservice.controller.vaccine.dao.CowinResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CowinClient(
    val restTemplate: RestTemplate
) {
    fun getCowinDataByPincode(pin_code: String, startDate: String): CowinResponse? {
        val url = "$COWIN_URL?pincode=$pin_code&date=$startDate"
        return restTemplate.getForEntity(url, CowinResponse::class.java).body
    }
}