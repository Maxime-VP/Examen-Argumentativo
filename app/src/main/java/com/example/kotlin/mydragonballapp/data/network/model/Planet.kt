package com.example.kotlin.mydragonballapp.data.network.model

data class Planet(
    val id: Int,
    val name: String,
    val isDestroyed: Boolean,
    val description: String,
    val image: String
)
