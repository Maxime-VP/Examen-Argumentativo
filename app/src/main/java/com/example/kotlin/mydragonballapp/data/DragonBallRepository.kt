
package com.example.kotlin.mydragonballapp.data

import com.example.kotlin.mydragonballapp.data.network.DragonBallApiClient
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase

class DragonBallRepository {

    private val apiClient = DragonBallApiClient()

    suspend fun getCharacterList(page: Int, limit: Int): CharactersObject? {
        return apiClient.getCharacterList(page, limit)
    }

    suspend fun getCharacterInfo(characterId: Int): CharacterBase? {
        return apiClient.getCharacterInfo(characterId)
    }
}
