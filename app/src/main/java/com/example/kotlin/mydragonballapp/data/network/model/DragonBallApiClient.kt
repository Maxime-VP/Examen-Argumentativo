package com.example.kotlin.mydragonballapp.data.network

import android.util.Log
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import retrofit2.Response

class DragonBallApiClient {
    private val api: DragonBallAPIService = NetworkModuleDI()

    // Método para obtener la lista de personajes
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
            e.printStackTrace()
            null
        }
    }


    // Método para obtener la información de un personaje específico por ID
    suspend fun getCharacterInfo(characterId: Int): CharacterBase? {
        return try {
            val response: Response<CharacterBase> = api.getCharacterInfo(characterId)
            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("DragonBallApiClient", "Error: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
