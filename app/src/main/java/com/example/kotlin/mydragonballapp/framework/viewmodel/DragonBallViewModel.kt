package com.example.kotlin.mydragonballapp.framework.viewmodel

import CharacterListRequirement
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DragonBallViewModel: ViewModel() {

    val charactersLiveData = MutableLiveData<CharactersObject?>()
    private val characterListRequirement = CharacterListRequirement()

    fun getCharacterList(page: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: CharactersObject? = characterListRequirement(page, limit)
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    charactersLiveData.postValue(result)
                }
            }
        }
    }
}


