package com.udemy.mvvmpokemon.view.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.udemy.mvvmpokemon.databinding.PokemonRowBinding
import com.udemy.mvvmpokemon.model.PokemonDataModel

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = PokemonRowBinding.bind(view)

    fun render(model: PokemonDataModel) {

        binding.name = model.name
        Glide.with(binding.root.context).load(model.img).into(binding.imgCharacter)
    }
}
