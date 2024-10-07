package com.example.kotlin.mydragonballapp.domain

import com.example.kotlin.mydragonballapp.data.DragonBallRepository
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase

class CharacterDetailRequirement {
    private val repository = DragonBallRepository()

    suspend operator fun invoke(characterId: Int): CharacterBase? {
        return repository.getCharacterInfo(characterId)
    }
}
