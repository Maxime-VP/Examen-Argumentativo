package com.example.kotlin.mydragonballapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.databinding.ItemCharacterBinding

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var data: List<CharacterBase> = emptyList()
    private lateinit var context: Context

    // Método para configurar los datos en el adaptador
    fun setData(newData: List<CharacterBase>, context: Context) {
        this.data = newData
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterBase) {
            // Asignar el nombre del personaje al TextView
            binding.TVName.text = character.name

            // Cargar la imagen usando Glide
            Glide.with(context)
                .load(character.image)
                .into(binding.IVPhoto)
        }
    }
}
