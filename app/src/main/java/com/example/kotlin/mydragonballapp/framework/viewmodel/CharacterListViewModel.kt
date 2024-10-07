package com.example.kotlin.mydragonballapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mydragonballapp.data.network.CharacterListRequirement
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log

class CharacterListViewModel : ViewModel() {

    val charactersLiveData = MutableLiveData<List<CharacterBase>>()
    private val characterListRequirement = CharacterListRequirement()
    private var currentPage = 1
    private var isLoading = false

    fun getCharacterList(limit: Int) {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = characterListRequirement(currentPage, limit)
                if (result != null) {
                    val currentList = charactersLiveData.value?.toMutableList() ?: mutableListOf()
                    currentList.addAll(result.items)
                    CoroutineScope(Dispatchers.Main).launch {
                        charactersLiveData.postValue(currentList)
                        currentPage++
                        isLoading = false
                    }
                } else {
                    Log.e("CharacterListViewModel", "API response: No characters returned.")
                    isLoading = false
                }
            } catch (e: Exception) {
                Log.e("CharacterListViewModel", "Error fetching characters: ${e.message}")
                isLoading = false
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
                        Log.e("CharacterListViewModel", "No characters found.")
                    }
                }
            } catch (e: Exception) {
                Log.e("CharacterListViewModel", "Error searching characters: ${e.message}")
            }
        }
    }
}
