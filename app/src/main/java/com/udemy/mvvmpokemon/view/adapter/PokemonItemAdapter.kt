package com.udemy.mvvmpokemon.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udemy.mvvmpokemon.R
import com.udemy.mvvmpokemon.model.PokemonDataModel

class PokemonItemAdapter(
    private val listPokemon: MutableList<PokemonDataModel>, private val onItemClick: (PokemonDataModel) -> Unit
) : RecyclerView.Adapter<PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_row, parent, false)
        return PokemonViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.render(listPokemon[position])
        holder.itemView.setOnClickListener {
            onItemClick(listPokemon[position])
        }
    }

    override fun getItemCount(): Int = listPokemon.size

}