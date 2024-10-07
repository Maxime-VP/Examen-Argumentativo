package com.example.kotlin.mydragonballapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.databinding.ItemCharacterBinding
import com.example.kotlin.mydragonballapp.framework.adapters.viewholders.CharacterViewHolder

class CharacterAdapter : RecyclerView.Adapter<CharacterViewHolder>() {
    private var data: ArrayList<CharacterBase> = ArrayList()
    private lateinit var context: Context

    // Método para configurar los datos en el adaptador
    fun setData(characterData: ArrayList<CharacterBase>, context: Context) {
        this.data = characterData
        this.context = context
        notifyDataSetChanged() // Notifica a la vista que los datos han cambiado
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
