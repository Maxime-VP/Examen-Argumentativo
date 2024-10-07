package com.example.kotlin.mydragonballapp.data.network.model

import com.google.gson.annotations.SerializedName

// CharactersObject.kt
data class CharactersObject(
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: ArrayList<CharacterBase>
)