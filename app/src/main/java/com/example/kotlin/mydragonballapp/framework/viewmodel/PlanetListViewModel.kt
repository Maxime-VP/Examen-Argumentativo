package com.example.kotlin.mydragonballapp.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.mydragonballapp.data.network.model.Planet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import com.example.kotlin.mydragonballapp.data.repository.PlanetRepository

class PlanetListViewModel : ViewModel() {

    val planetLiveData = MutableLiveData<List<Planet>?>()
    private val repository = PlanetRepository()

    fun getPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val planets = repository.getPlanets()
                planetLiveData.postValue(planets?.items)
            } catch (e: Exception) {
                Log.e("PlanetListViewModel", "Error fetching planets: ${e.message}")
            }
        }
    }
}
