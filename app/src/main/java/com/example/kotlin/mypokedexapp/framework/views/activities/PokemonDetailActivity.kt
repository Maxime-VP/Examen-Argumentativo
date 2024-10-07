package com.example.kotlin.mypokedexapp.framework.views.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.kotlin.mypokedexapp.databinding.ActivityPokemonDetailBinding
import com.example.kotlin.mypokedexapp.utils.Constants

class PokemonDetailActivity: Activity() {
    private lateinit var binding: ActivityPokemonDetailBinding
    private var pokemonUrl:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        manageIntent()

        initializeBinding()

    }

    private fun initializeBinding(){
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun manageIntent(){
        if(intent != null){
            pokemonUrl = intent.getStringExtra(Constants.URL_POKEMON)


            Log.d("Salida 1",pokemonUrl.toString())
        }
    }
}
