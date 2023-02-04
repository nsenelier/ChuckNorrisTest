package com.example.chucknorristest.usecases.jokes

import android.location.Location
import com.example.chucknorristest.model.Joke
import com.example.chucknorristest.rest.JokeRepository
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomUseCases @Inject constructor(
    private val jokeRepository: JokeRepository
){

    fun getRandom(): Flow<UIState> = flow{
        jokeRepository.getRandom()
    }

}