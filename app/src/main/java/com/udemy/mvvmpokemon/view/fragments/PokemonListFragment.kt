package com.udemy.mvvmpokemon.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.udemy.mvvmpokemon.R
import com.udemy.mvvmpokemon.databinding.FragmentPokemonListBinding
import com.udemy.mvvmpokemon.model.PokemonDataModel
import com.udemy.mvvmpokemon.view.adapter.PokemonItemAdapter
import com.udemy.mvvmpokemon.viewmodels.RecyclerPokemonViewModel


class PokemonListFragment : Fragment() {

    private lateinit var mAdapter: PokemonItemAdapter
    private lateinit var viewModel: RecyclerPokemonViewModel
    private lateinit var binding: FragmentPokemonListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity.let {
            ViewModelProvider(it!!)[RecyclerPokemonViewModel::class.java]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val layoutInflater = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        binding = FragmentPokemonListBinding.bind(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.listState.observe(viewLifecycleOwner) {
            mAdapter = PokemonItemAdapter(it) { pokemon -> onItemClick(pokemon) }
            binding.recyclerview.layoutManager = LinearLayoutManager(context)
            binding.recyclerview.adapter = mAdapter
            binding.progress.isInvisible = true
        }

        viewModel.progressState.observe(viewLifecycleOwner) {
            binding.progress.isVisible = true
        }

        viewModel.fetchPokemonData()

    }

    private fun onItemClick(pokemon: PokemonDataModel) {
        viewModel.setItemSelection(pokemon)
        activity?.supportFragmentManager?.beginTransaction()?.replace(android.R.id.content, PokemonDetailFragment.newInstance())
            ?.addToBackStack(null)?.commit()
    }
}