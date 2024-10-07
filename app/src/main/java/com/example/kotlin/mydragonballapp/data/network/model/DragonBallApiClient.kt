package com.example.kotlin.mydragonballapp.data.network

import android.util.Log
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharacterListResponse
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject

class DragonBallApiClient {
    private val api: DragonBallAPIService = NetworkModuleDI()

    suspend fun getCharacterList(page: Int, limit: Int): CharactersObject? {
        return try {
            val response = api.getCharacterList(page, limit)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("API_ERROR", "API call failed: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            null
        }
    }

    suspend fun searchCharactersByName(name: String): CharacterListResponse? {
        return try {
            val response = api.getCharactersByName(name)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("API_ERROR", "API call failed: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            null
        }
    }

    // Método para obtener información de un personaje específico por ID
    suspend fun getCharacterInfo(characterId: Int): CharacterBase? {
        return try {
            val response = api.getCharacterInfo(characterId)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("API_ERROR", "API call failed: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_EXCEPTION", "Exception occurred: ${e.message}")
            null
        }
    }
}
