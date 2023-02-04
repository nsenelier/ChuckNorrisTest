package com.example.chucknorristest.rest

import com.example.chucknorristest.model.*

import com.example.chucknorristest.viewModel.utils.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface JokeAPI{
    //https://api.chucknorris.io/jokes/random
    //https://api.chucknorris.io/jokes/search?query={query}
    //https://api.chucknorris.io/jokes/categories
    //https://api.chucknorris.io/jokes/random?category={category}

    @GET(ENDPOINT_RANDOM)
    suspend fun getRandomJoke(): Response<Joke>

    @GET(ENDPOINT_SEARCH)
    suspend fun  getSearchJoke(
        @Query(PARAM_QUERY) searchInput: String? = null
    ): Response<QueryResponse>

    @GET(ENDPOINT_CATEGORIES)
    suspend fun getCategoriesJoke(): Response<Category>

    @GET(ENDPOINT_RANDOM)
    suspend fun getRandomCategoriesJoke(
        @Query(PARAM_CATEGORIES) categoryInput: String
    ): Response<CategoryResponse>

}