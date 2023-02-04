package com.example.chucknorristest.rest

import com.example.chucknorristest.model.*
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface JokeRepository {
  val data: StateFlow<UIState>
  suspend fun getRandom()
  fun getSearch(input: String? = null): Flow<UIState>
  fun getCategory(): Flow<UIState>
  fun getCategoryJoke(input: String): Flow<UIState>
}

class JokeRepositoryImpl @Inject constructor(
  private val serviceApi: JokeAPI
): JokeRepository {

  private val _data: MutableStateFlow<UIState> = MutableStateFlow(UIState.Empty)
  override val data: StateFlow<UIState> get() = _data

  override suspend fun getRandom(){
    _data.value = UIState.Loading

    try {
      val response = serviceApi.getRandomJoke()
      if (response.isSuccessful) {
        response.body()?.let {
          _data.value = UIState.Success(it.value ?: "")
        } ?: throw Exception("No Random Joke available")
      } else {
        throw Exception(response.errorBody()?.string())
      }
    } catch (e: Exception) {
      _data.value = UIState.Failure(e)
    }
  }

  override fun getSearch(input: String?): Flow<UIState> = flow {
    emit(UIState.Loading)

    try {
      val response = serviceApi.getSearchJoke(input)
      if (response.isSuccessful) {
        response.body()?.let {
          emit(UIState.Success(it.result ?: emptyList()))
        } ?: throw Exception("Jokes are null")
      } else {
        throw Exception(response.errorBody()?.string())
      }
    } catch (e: Exception) {
      emit(UIState.Failure(e))
    }
  }

  override fun getCategory(): Flow<UIState> = flow {
    emit(UIState.Loading)

    try {
      val response = serviceApi.getCategoriesJoke()
      if (response.isSuccessful) {
        response.body()?.let {
          emit(UIState.Success(it))
        } ?: throw Exception("Categories are null")
      } else {
        throw Exception(response.errorBody()?.string())
      }
    } catch (e: Exception) {
      emit(UIState.Failure(e))
    }
  }

  override fun getCategoryJoke(input: String): Flow<UIState> = flow {
    emit(UIState.Loading)

    try {
      val response = serviceApi.getRandomCategoriesJoke(input)
      if (response.isSuccessful) {
        response.body()?.let {
          emit(UIState.Success(it))
        } ?: throw Exception("No random joke for this category")
      } else {
        throw Exception(response.errorBody()?.string())
      }
    } catch (e: Exception) {
      emit(UIState.Failure(e))
    }
  }

}