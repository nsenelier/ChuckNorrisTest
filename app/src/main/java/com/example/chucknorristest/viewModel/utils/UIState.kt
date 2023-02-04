package com.example.chucknorristest.viewModel.utils


sealed class UIState{
    object Loading: UIState()
    class Failure(val errorMessage: Exception): UIState()
    data class Success<T: Any> (val response: T): UIState()
    object Empty: UIState() //may not use
}

