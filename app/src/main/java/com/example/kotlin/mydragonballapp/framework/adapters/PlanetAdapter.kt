package com.example.kotlin.mydragonballapp.framework.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.mydragonballapp.R
import com.example.kotlin.mydragonballapp.data.network.model.Planet
import com.example.kotlin.mydragonballapp.framework.adapters.viewholders.PlanetViewHolder

class PlanetAdapter : RecyclerView.Adapter<PlanetViewHolder>() {

    private var planetList: List<Planet> = emptyList()
    private lateinit var context: Context

    fun setData(newData: List<Planet>, context: Context) {
        this.planetList = newData
        this.context = context
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planet, parent, false)
        return PlanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(planetList[position], context)
    }

    override fun getItemCount(): Int = planetList.size
}
