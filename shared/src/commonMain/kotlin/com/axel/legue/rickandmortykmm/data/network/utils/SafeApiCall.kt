package com.axel.legue.rickandmortykmm.data.network.utils

import com.axel.legue.rickandmortykmm.data.network.utils.NetworkResult.Failure
import com.axel.legue.rickandmortykmm.data.network.models.ApiError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.util.InternalAPI
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
suspend fun <T : Any> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
    return try {
        NetworkResult.Success(data = apiCall.invoke())
    } catch (e: RedirectResponseException) { // 3xx errors
        val networkError = getError(responseContent = e.response.content)
        Failure(
            errorCode = networkError.statusCode ?: e.response.status.value,
            errorMessage = networkError.statusMessage ?: e.message
        )
    } catch (e: ClientRequestException) { // 4xx errors
        val networkError = getError(responseContent = e.response.content)
        Failure(
            errorCode = networkError.statusCode ?: e.response.status.value,
            errorMessage = networkError.statusMessage ?: e.message
        )
    } catch (e: ServerResponseException) { // 5xx errors
        val networkError = getError(responseContent = e.response.content)
        Failure(
            errorCode = networkError.statusCode ?: e.response.status.value,
            errorMessage = networkError.statusMessage ?: e.message
        )
    } catch (e: Exception) {
        Failure(
            errorCode = 0,
            errorMessage = e.message ?: "An unknown error occurred"
        )
    }
}

fun getError(responseContent: ByteReadChannel): ApiError {
    return Json.decodeFromString(string = responseContent.toString())
    // throw IllegalArgumentException("Not a parsable error")
}