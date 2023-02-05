package com.krivochkov.krivochkov.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilmsViewModel : ViewModel() {

    private val _selectedCategory = MutableLiveData(FilmsCategory.POPULAR)
    val selectedCategory: LiveData<FilmsCategory>
        get() = _selectedCategory

    fun selectCategoryByPosition(position: Int) {
        val category = findCategoryByPosition(position)
        selectCategory(category)
    }

    fun selectCategory(category: FilmsCategory) {
        _selectedCategory.value = category
    }

    private fun findCategoryByPosition(position: Int): FilmsCategory {
        return if (position == 1) FilmsCategory.FAVOURITE else FilmsCategory.POPULAR
    }

    enum class FilmsCategory(val position: Int) {
        POPULAR(0),
        FAVOURITE(1)
    }
}