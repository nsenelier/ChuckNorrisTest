package com.example.chucknorristest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorristest.model.Category
import com.example.chucknorristest.usecases.jokes.CategoryUseCases
import com.example.chucknorristest.viewModel.utils.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategoryListViewModel @Inject internal constructor(
    private val categoryUseCases: CategoryUseCases
): ViewModel() {

    private val _data = MutableLiveData<UIState>()
    val data: LiveData<UIState>
        get() = _data

    var selectedCategory: Category? = null

    fun getCategoryJokes(){
        viewModelScope.launch {
            selectedCategory?.name?.let {
                categoryUseCases.selectedCategory(it).collect{ state ->
                    _data.postValue(state)
                }
            }
        }
    }

}