package com.example.kotlin.mydragonballapp.data.network.model.personajes

data class Item(
    val affiliation: String,
    val deletedAt: Any,
    val description: String,
    val gender: String,
    val id: Int,
    val image: String,
    val ki: String,
    val maxKi: String,
    val name: String,
    val race: String
)