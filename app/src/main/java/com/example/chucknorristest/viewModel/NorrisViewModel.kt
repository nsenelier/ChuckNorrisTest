package com.example.chucknorristest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorristest.model.Category
import com.example.chucknorristest.model.CategoryResponse
import com.example.chucknorristest.model.Joke

import com.example.chucknorristest.model.QueryResponse
import com.example.chucknorristest.usecases.jokes.CategoryUseCases
import com.example.chucknorristest.usecases.jokes.RandomUseCases
import com.example.chucknorristest.usecases.jokes.SearchUseCases
import com.example.chucknorristest.viewModel.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NorrisViewModel @Inject constructor(
 private val randomUseCases: RandomUseCases,
 private val searchUseCases: SearchUseCases,
 private val categoryUseCases: CategoryUseCases
): ViewModel() {

    private val _data = MutableLiveData<UIState>()
    val data: LiveData<UIState>
        get() = _data

    var selectedCategory: Category? = null
    var input: QueryResponse? = null
    var joke: Joke? = null
    var resultCategory: CategoryResponse? = null
    var listJokes: List<Joke>? = null




}