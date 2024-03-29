package com.udemy.mvvmpokemon.services

import androidx.viewbinding.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/"
    private var retrofit: Retrofit? = null

    private val logger: OkHttpClient
        get() {
            val loggin = HttpLoggingInterceptor()
            loggin.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
            //if(BuildConfig) httpClient.interceptors().add(loggin)
            return httpClient.build()
        }

    val getClient:Retrofit?
    get(){
        if(retrofit==null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(logger)
                .build()
        }
        return retrofit
    }

}