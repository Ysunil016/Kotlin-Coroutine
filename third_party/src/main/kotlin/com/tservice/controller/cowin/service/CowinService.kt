package com.tservice.controller.cowin.service

import com.tservice.controller.cowin.client.CowinClient
import com.tservice.controller.cowin.dao.CowinResponse
import com.tservice.controller.cowin.dao.CowinSession
import com.tservice.controller.cowin.dao.Hospital
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class CowinService(
    val cowinClient: CowinClient
) {

    fun getValue(pin_code: String, ageLimit: Int): List<Hospital> {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDate = sdf.format(Date())
        val response = cowinClient.getCowinDataByPincode(pin_code, currentDate) ?: return listOf()
        return findHospital(response, ageLimit)
    }

    private fun findHospital(response: CowinResponse, ageLimit: Int): List<Hospital> {
        return response.centers.filter { isSessionsValid(it.sessions, ageLimit) }.map {
            it.copy(sessions = checkingIfSession(it.sessions))
        }
    }

    private fun checkingIfSession(sessions: List<CowinSession>): List<CowinSession> {
        return sessions.filter { it.available_capacity > 0 }
    }

    private fun isSessionsValid(sessions: List<CowinSession>, ageLimit: Int): Boolean {
        return sessions.any { isAgeInLimit(it, ageLimit) }
    }

    private fun isAgeInLimit(session: CowinSession, ageLimit: Int): Boolean {
        return session.min_age_limit in 5..ageLimit && session.available_capacity > 0
    }
}


