package com.example.kotlin.mydragonballapp.data.network.model

data class PlanetResponse(
    val items: List<Planet>,
    val meta: Meta
)

data class Meta(
    val totalItems: Int,
    val itemCount: Int,
    val itemsPerPage: Int,
    val totalPages: Int,
    val currentPage: Int
)
