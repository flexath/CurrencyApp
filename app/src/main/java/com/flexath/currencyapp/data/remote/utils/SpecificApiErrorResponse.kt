package com.flexath.currencyapp.data.remote.utils

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.HttpException

class SpecificApiErrorResponse(error: Throwable) {
    var errors: SpecificErrorResponse? = null

    init {
        if (error is HttpException) {
            val errorJsonString = error.response()?.errorBody()?.string()
            this.errors = parseErrorJson(errorJsonString)
        } else {
            this.errors = SpecificErrorResponse("An unknown error occurred", null)
        }
    }

    private fun parseErrorJson(jsonString: String?): SpecificErrorResponse {
        return try {
            val jsonObject = JsonParser.parseString(jsonString).asJsonObject
            val errorObject = jsonObject.getAsJsonObject("error")
            val code = errorObject.get("code").asInt
            val info = errorObject.get("info").asString
            SpecificErrorResponse(info, code)
        } catch (e: Exception) {
            SpecificErrorResponse("Failed to parse error response", null)
        }
    }
}

data class SpecificErrorResponse(
    val message: String,
    val code: Int?
)
