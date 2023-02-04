package com.example.chucknorristest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorristest.rest.JokeRepository
import com.example.chucknorristest.usecases.jokes.RandomUseCases
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomViewModel @Inject constructor(
    private val randomUseCases: RandomUseCases,
    jokeRepository: JokeRepository
): ViewModel() {

    private val _data = MutableLiveData<UIState>()
    val data: LiveData<UIState>
        get() = _data


    private val randomButtonText = MutableLiveData<String>()
    init {
        randomButtonText.value = "Random"
    }

    fun getUnknownJokes(){
        viewModelScope.launch {
            randomUseCases.getRandom().collect{
                _data.postValue(it)
            }
        }
    }
}