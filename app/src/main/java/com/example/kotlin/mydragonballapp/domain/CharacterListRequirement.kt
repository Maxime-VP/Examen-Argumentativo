package com.example.kotlin.mydragonballapp.data.network

import android.util.Log
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharacterListResponse
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject

class CharacterListRequirement {
    private val apiClient = DragonBallApiClient()

    suspend operator fun invoke(page: Int, limit: Int): CharactersObject? {
        return try {
            val response = apiClient.getCharacterList(page, limit)
            if (response != null) {
                response
            } else {
                Log.e("CharacterListRequirement", "No characters found in paginated call.")
                null
            }
        } catch (e: Exception) {
            Log.e("CharacterListRequirement", "Exception occurred: ${e.message}")
            null
        }
    }

    suspend fun searchCharactersByName(name: String): CharacterListResponse? {
        return try {
            val response = apiClient.searchCharactersByName(name)
            if (response != null) {
                response
            } else {
                Log.e("CharacterListReq", "No characters found for name: $name")
                null
            }
        } catch (e: Exception) {
            Log.e("CharacterListReq", "Exception occurred during search: ${e.message}")
            null
        }
    }

    // Método para obtener información de un personaje por ID
    suspend fun getCharacterInfo(characterId: Int): CharacterBase? {
        return try {
            val response = apiClient.getCharacterInfo(characterId)
            if (response != null) {
                response
            } else {
                Log.e("CharacterListReq", "No character found with ID: $characterId")
                null
            }
        } catch (e: Exception) {
            Log.e("CharacterListReq", "Exception occurred: ${e.message}")
            null
        }
    }
}
