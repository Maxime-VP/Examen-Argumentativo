package com.example.kotlin.mydragonballapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.databinding.ItemCharacterBinding
import com.example.kotlin.mydragonballapp.framework.adapters.viewholders.DragonBallViewHolder

class CharacterAdapter(private val context: Context) : RecyclerView.Adapter<DragonBallViewHolder>() {

    private var data: MutableList<CharacterBase> = mutableListOf()

    fun setData(newData: List<CharacterBase>) {
        data.clear() // Limpia la lista anterior si quieres reemplazarla por completo
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonBallViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonBallViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DragonBallViewHolder, position: Int) {
        holder.bind(data[position], context)
    }

    override fun getItemCount(): Int = data.size
}
