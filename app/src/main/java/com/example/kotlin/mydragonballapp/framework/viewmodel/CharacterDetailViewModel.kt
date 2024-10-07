package com.example.kotlin.mydragonballapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.domain.CharacterDetailRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    val characterDetailLiveData = MutableLiveData<CharacterBase>()
    private val characterDetailRequirement = CharacterDetailRequirement()

    fun getCharacterDetail(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: CharacterBase? = characterDetailRequirement(characterId)
            CoroutineScope(Dispatchers.Main).launch {
                if (result != null) {
                    characterDetailLiveData.postValue(result)
                }
            }
        }
    }
}
