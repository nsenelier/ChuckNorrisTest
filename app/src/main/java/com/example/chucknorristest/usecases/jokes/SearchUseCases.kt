package com.example.chucknorristest.usecases.jokes

import com.example.chucknorristest.rest.JokeRepository
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCases @Inject constructor(
    private val jokeRepository: JokeRepository
){
    fun getSearch(input: String): Flow<UIState> = jokeRepository.getSearch(input)
}