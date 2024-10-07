package com.example.kotlin.mydragonballapp.domain

import com.example.kotlin.mydragonballapp.data.DragonBallRepository
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject

class CharacterListRequirement {

    private val repository = DragonBallRepository()

    suspend operator fun invoke(page: Int, limit: Int): CharactersObject? {
        return repository.getCharacterList(page, limit)
    }
}
