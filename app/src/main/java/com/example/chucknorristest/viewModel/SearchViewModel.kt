package com.example.chucknorristest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorristest.rest.JokeRepository
import com.example.chucknorristest.usecases.jokes.RandomUseCases
import com.example.chucknorristest.usecases.jokes.SearchUseCases
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases,

): ViewModel() {
    private val inputQuery = MutableLiveData<String>()
    private val searchButtonText = MutableLiveData<String>()

    private val _data = MutableLiveData<UIState>()
    val data: LiveData<UIState>
        get() = _data

    init {
        searchButtonText.value = "Search"
    }

    fun update(){
        val query = inputQuery.value!!
        getSearchJokes(query)
        inputQuery.value = ""
    }

    private fun getSearchJokes(input: String){
        viewModelScope.launch {
            searchUseCases.getSearch(input).collect{
                _data.postValue(it)
            }
        }
    }
}