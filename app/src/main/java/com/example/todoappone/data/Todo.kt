package com.example.todoappone.data

import java.util.Date

data class Todo(
    val title: String,
    val description: String,
    val createdAt: Date,
    val isFav: Boolean = false
)
