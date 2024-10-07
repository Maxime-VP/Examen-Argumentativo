package com.example.kotlin.mydragonballapp.data.network

import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallAPIService {

    // Método para obtener la lista de personajes con paginación
    @GET("characters")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): CharactersObject

    // Método para obtener los detalles de un personaje específico por su ID
    @GET("characters/{id}")
    suspend fun getCharacterInfo(
        @Path("id") characterId: Int
    ): CharacterBase
}
