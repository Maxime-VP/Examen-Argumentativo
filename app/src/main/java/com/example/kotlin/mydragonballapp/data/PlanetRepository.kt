package com.example.kotlin.mydragonballapp.data.repository

import com.example.kotlin.mydragonballapp.data.network.PlanetRequirement
import com.example.kotlin.mydragonballapp.data.network.model.PlanetResponse

class PlanetRepository {

    private val planetRequirement = PlanetRequirement()

    suspend fun getPlanets(): PlanetResponse? {
        return planetRequirement.getPlanets()
    }
}
