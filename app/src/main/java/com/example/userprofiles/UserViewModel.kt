package com.example.userprofiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _todo = MutableStateFlow<Todo?>(null)
    val todo: StateFlow<Todo?> = _todo

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init { fetchRandomTodo() }

    fun fetchRandomTodo() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.getRandomTodo()
                _todo.value = response.firstOrNull()
            } catch (e: Exception) {
                _error.value = "Failed to load user: ${e.message}"
            }
            _loading.value = false
        }
    }
}
