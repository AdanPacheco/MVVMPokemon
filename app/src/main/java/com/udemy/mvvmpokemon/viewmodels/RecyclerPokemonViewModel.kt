package com.udemy.mvvmpokemon.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.udemy.mvvmpokemon.model.PokemonDataModel
import com.udemy.mvvmpokemon.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecyclerPokemonViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {


    private val _itemSelected = MutableLiveData<PokemonDataModel?>()
    var itemDataSelected: PokemonDataModel? = null

    private val _listState = MutableLiveData<MutableList<PokemonDataModel>>()
    var listState: LiveData<MutableList<PokemonDataModel>> = _listState

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = PokemonRepository()
    private lateinit var observerOnCategorySelected: Observer<PokemonDataModel>

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default

    init {
        initObserver()
    }

    private fun initObserver() {
        observerOnCategorySelected = Observer { value ->
            value.let { pokemon ->
                _itemSelected.value = pokemon
            }
        }
    }


    fun setItemSelection(item: PokemonDataModel) {
        itemDataSelected = item
    }

    fun fetchPokemonData() {
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getPokemon()
            response?.body()?.pokemon.let{
                    list->
                _listState.value = list
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}