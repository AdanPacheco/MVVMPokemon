package com.udemy.mvvmpokemon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.udemy.mvvmpokemon.R
import com.udemy.mvvmpokemon.databinding.FragmentPokemonDetailBinding
import com.udemy.mvvmpokemon.viewmodels.RecyclerPokemonViewModel

class PokemonDetailFragment : Fragment() {

    private lateinit var _binding: FragmentPokemonDetailBinding
    private val binding get() = _binding

    private lateinit var viewModel: RecyclerPokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity.let {
            ViewModelProvider(it!!)[RecyclerPokemonViewModel::class.java]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.itemDataSelected?.let { pokemonData ->
            Glide.with(requireContext()).load(pokemonData.img).into(binding.img)
            binding.tvName.text = pokemonData.name
        }
    }
    
    companion object{
        fun newInstance()=PokemonDetailFragment()
    }
}