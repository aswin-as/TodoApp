package com.example.todoappone

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoappone.data.TodoListViewModel


enum class Screen() {
    Home,
    TodoPage
}

@Composable
fun TodoAppScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {

    val todoListViewModel: TodoListViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name
    ) {
        composable(route = Screen.Home.name){
            HomePage(
                navController = navController,
                viewModel = todoListViewModel
            )
        }

        composable(route = Screen.TodoPage.name){
            TodoListpage(
                navController = navController,
                viewModel = todoListViewModel
            )
        }
    }
}