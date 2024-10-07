package com.example.kotlin.mydragonballapp.data.network

import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharacterListResponse
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DragonBallAPIService {

    @GET("characters")
    suspend fun getCharacterList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<CharactersObject>

    @GET("characters/{id}")
    suspend fun getCharacterInfo(
        @Path("id") characterId: Int
    ): Response<CharacterBase>

    // Actualización del método para la búsqueda por nombre
    @GET("characters")
    suspend fun getCharactersByName(
        @Query("name") name: String
    ): Response<CharacterListResponse> // Cambiado para reflejar que devuelve un array
}
