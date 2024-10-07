package com.example.kotlin.mydragonballapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mydragonballapp.data.network.CharacterListRequirement
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.data.network.model.CharacterListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class DragonBallViewModel : ViewModel() {

    val charactersLiveData = MutableLiveData<List<CharacterBase>?>()
    private val characterListRequirement = CharacterListRequirement()

    fun getCharacterList(page: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = characterListRequirement(page, limit)
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    charactersLiveData.postValue(result.items)
                }
            }
        }
    }

    fun searchCharactersByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = characterListRequirement.searchCharactersByName(name)
                CoroutineScope(Dispatchers.Main).launch {
                    if (result != null) {
                        charactersLiveData.postValue(result)
                    } else {
                        Log.e("DragonBallViewModel", "No characters found.")
                    }
                }
            } catch (e: Exception) {
                Log.e("DragonBallViewModel", "Error searching characters: ${e.message}")
            }
        }
    }

    fun getCharacterInfo(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = characterListRequirement.getCharacterInfo(characterId)
                CoroutineScope(Dispatchers.Main).launch {
                    if (result != null) {
                        // Aquí podrías actualizar un LiveData específico para los detalles del personaje
                        Log.d("DragonBallViewModel", "Character info: ${result.name}")
                    } else {
                        Log.e("DragonBallViewModel", "No character found with ID: $characterId")
                    }
                }
            } catch (e: Exception) {
                Log.e("DragonBallViewModel", "Error fetching character info: ${e.message}")
            }
        }
    }
}
