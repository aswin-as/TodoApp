package com.example.todoappone.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

class TodoListViewModel: ViewModel() {

    private val _todoItems = MutableStateFlow<List<Todo>>(emptyList())

    val todoItems: StateFlow<List<Todo>> = _todoItems.asStateFlow()

    fun addItems(title: String, description: String, createdAt: Date = Date(),isFav: Boolean = false){
        val newTodoITem = Todo(title,description,createdAt,isFav)
        _todoItems.value = _todoItems.value + newTodoITem
    }

    fun deleteItem(item: Todo){
        _todoItems.value = _todoItems.value - item
    }

}