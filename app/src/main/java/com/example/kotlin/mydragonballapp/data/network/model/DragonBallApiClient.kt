package com.example.kotlin.mydragonballapp.data.network

import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject

class DragonBallApiClient {
    private val api: DragonBallAPIService = NetworkModuleDI()

    // Método para obtener la lista de personajes
    suspend fun getCharacterList(page: Int, limit: Int): CharactersObject? {
        return try {
            api.getCharacterList(page, limit)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Método para obtener la información de un personaje específico por ID
    suspend fun getCharacterInfo(characterId: Int): CharacterBase? {
        return try {
            api.getCharacterInfo(characterId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
