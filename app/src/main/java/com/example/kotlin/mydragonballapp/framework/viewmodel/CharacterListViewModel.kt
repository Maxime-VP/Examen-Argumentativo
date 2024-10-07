package com.example.kotlin.mydragonballapp.framework.viewmodel

import CharacterListRequirement
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mydragonballapp.data.network.model.CharactersObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    val charactersLiveData = MutableLiveData<CharactersObject?>()
    private val characterListRequirement = CharacterListRequirement()

    fun getCharacterList(page: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result: CharactersObject? = characterListRequirement(page, limit)
                if (result != null) {
                    Log.d("CharacterListViewModel", "API response: ${result.items.size} characters loaded.")
                    CoroutineScope(Dispatchers.Main).launch {
                        charactersLiveData.postValue(result)
                    }
                } else {
                    Log.e("CharacterListViewModel", "API response: No characters returned.")
                }
            } catch (e: Exception) {
                Log.e("CharacterListViewModel", "Error fetching characters: ${e.message}")
            }
        }
    }
}
