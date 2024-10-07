package com.example.kotlin.mydragonballapp.data.network

import android.util.Log
import com.example.kotlin.mydragonballapp.data.network.model.PlanetResponse

class PlanetRequirement {
    private val apiService = NetworkModuleDI()

    suspend fun getPlanets(): PlanetResponse? {
        return try {
            val response = apiService.getPlanets()
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("PlanetRequirement", "API call failed: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("PlanetRequirement", "Exception occurred: ${e.message}")
            null
        }
    }
}
