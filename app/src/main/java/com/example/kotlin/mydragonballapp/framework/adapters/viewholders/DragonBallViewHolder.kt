package com.example.kotlin.mydragonballapp.framework.adapters.viewholders

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin.mydragonballapp.data.network.model.CharacterBase
import com.example.kotlin.mydragonballapp.databinding.ItemCharacterBinding
import com.example.kotlin.mydragonballapp.framework.views.activities.CharacterDetailActivity
import com.example.kotlin.mydragonballapp.utils.Constants

class DragonBallViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CharacterBase, context: Context) {
        binding.TVName.text = item.name
        loadImage(item.image, binding.IVPhoto, context)

        binding.llCharacter.setOnClickListener {
            goToCharacterDetail(item.id, context)
        }
    }

    private fun goToCharacterDetail(characterId: Int, context: Context) {
        val intent = Intent(context, CharacterDetailActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        intent.putExtra(Constants.CHARACTER_ID, characterId)
        context.startActivity(intent)
    }

    private fun loadImage(url: String, imageView: ImageView, context: Context) {
        val requestOptions = RequestOptions()
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .fitCenter()
            .priority(Priority.HIGH)

        Glide.with(context).load(url)
            .apply(requestOptions)
            .into(imageView)
    }
}
