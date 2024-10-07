package com.example.kotlin.mydragonballapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.databinding.ItemCharacterBinding
import com.example.kotlin.mydragonballapp.framework.adapters.viewholders.DragonBallViewHolder


class DragonBallAdapter: RecyclerView.Adapter<DragonBallViewHolder>() {
    var data: ArrayList<CharacterBase> = ArrayList()
    lateinit var context: Context

    fun CommonsAdapter(characterData: ArrayList<CharacterBase>, context: Context) {
        this.data = characterData
        this.context = context
    }

    override fun onBindViewHolder(holder: DragonBallViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragonBallViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DragonBallViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
