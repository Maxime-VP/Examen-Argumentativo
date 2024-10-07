package com.example.kotlin.mydragonballapp.framework.adapters.viewholders

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.mydragonballapp.R
import com.example.kotlin.mydragonballapp.data.network.model.Planet

class PlanetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val planetImage: ImageView = itemView.findViewById(R.id.IVPlanetImage)
    private val planetName: TextView = itemView.findViewById(R.id.TVPlanetName)
    private val planetDescription: TextView = itemView.findViewById(R.id.TVPlanetDescription)

    fun bind(planet: Planet, context: Context) {
        planetName.text = planet.name
        planetDescription.text = planet.description
        Glide.with(context).load(planet.image).into(planetImage)
    }
}
