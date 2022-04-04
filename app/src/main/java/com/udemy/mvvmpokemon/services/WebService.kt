package com.udemy.mvvmpokemon.services

import com.udemy.mvvmpokemon.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    @GET("pokedex.json")
    suspend fun getPokemons(): Response<PokemonResponse>
}