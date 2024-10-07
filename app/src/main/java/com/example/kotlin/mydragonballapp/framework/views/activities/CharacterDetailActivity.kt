package com.example.kotlin.mydragonballapp.framework.views.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.kotlin.mydragonballapp.databinding.ActivityCharacterDetailBinding

import com.example.kotlin.mydragonballapp.framework.viewmodel.CharacterDetailViewModel
import com.example.kotlin.mydragonballapp.utils.Constants

class CharacterDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterDetailBinding
    private val viewModel: CharacterDetailViewModel by viewModels()
    private var characterId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Manejo de intentos para recibir el ID del personaje
        manageIntent()
        // Inicializar binding de la actividad
        initializeBinding()
        // Inicializar observadores
        initializeObservers()
        // Obtener detalles del personaje desde el ViewModel
        viewModel.getCharacterDetail(characterId)
    }

    private fun manageIntent() {
        if (intent != null) {
            characterId = intent.getIntExtra(Constants.CHARACTER_ID, 0)
            Log.d("CharacterDetail", "Character ID: $characterId")
        }
    }

    private fun initializeBinding() {
        binding = ActivityCharacterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initializeObservers() {
        viewModel.characterDetailLiveData.observe(this, Observer { character ->
            if (character != null) {
                binding.TVCharacterName.text = character.name
                binding.TVCharacterDescription.text = character.description
                Glide.with(this)
                    .load(character.image)
                    .into(binding.IVCharacterImage)
            }
        })
    }
}
