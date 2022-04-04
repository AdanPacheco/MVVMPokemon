package com.udemy.mvvmpokemon.repository

import com.udemy.mvvmpokemon.services.RetrofitClient
import com.udemy.mvvmpokemon.services.WebService

class PokemonRepository {

    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    suspend fun getPokemon() = apiService?.getPokemons()
}