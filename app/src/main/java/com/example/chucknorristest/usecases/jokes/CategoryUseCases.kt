package com.example.chucknorristest.usecases.jokes

import com.example.chucknorristest.rest.JokeRepository
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryUseCases @Inject constructor(
    private val jokeRepository: JokeRepository
){
    fun getCategories(): Flow<UIState> = flow{
        jokeRepository.getCategory()

        //jokeRepository.
    }

    fun selectedCategory(input: String): Flow<UIState> =
        jokeRepository.getCategoryJoke(input)
}