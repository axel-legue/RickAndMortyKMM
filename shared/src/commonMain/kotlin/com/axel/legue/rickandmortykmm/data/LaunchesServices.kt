package com.axel.legue.rickandmortykmm.launches.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface LaunchesServices {
    interface LaunchService {
        companion object {
            const val END_POINT = "/v4/launches"
        }
    }

    @Serializable
    data class RocketLaunch(
        @SerialName("flight_number")
        val flightNumber: Int,
        @SerialName("name")
        val missionName: String,
        @SerialName("date_utc")
        val launchDateUTC: String,
        @SerialName("success")
        val launchSuccess: Boolean?
    )
}
