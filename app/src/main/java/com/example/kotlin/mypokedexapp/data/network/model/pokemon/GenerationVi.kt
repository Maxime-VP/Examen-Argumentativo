package com.example.kotlin.mypokedexapp.data.network.model.pokemon

import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire") val omegaruby_alphasapphire: com.example.kotlin.mypokedexapp.data.network.model.pokemon.OmegarubyAlphasapphire,
    @SerializedName("x-y") val x_y: com.example.kotlin.mypokedexapp.data.network.model.pokemon.XY
)