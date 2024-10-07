package com.example.kotlin.mypokedexapp.data.network

import com.example.kotlin.mypokedexapp.data.network.model.PokedexObject
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPIService {

    //https://pokeapi.co/api/v2/pokemon/?limit=1279
    @GET("pokemon") //metodo get a /pokemon
    suspend fun getPokemonList( //funcion asincrona
        @Query("limit") limit:Int //recibe un query param de la variable limit de tipo Int
    ): com.example.kotlin.mypokedexapp.data.network.model.PokedexObject //objeto recibido

    //https://pokeapi.co/api/v2/pokemon/{number_pokemon}/
    @GET("pokemon/{numberPokemon}")
    suspend fun getPokemonInfo(
        @Path("numberPokemon") numberPokemon:Int
    ) : com.example.kotlin.mypokedexapp.data.network.model.pokemon.Pokemon

}