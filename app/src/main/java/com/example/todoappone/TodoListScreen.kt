package com.example.todoappone

import android.graphics.fonts.FontStyle
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.todoappone.data.Todo
import com.example.todoappone.data.TodoListViewModel
import java.time.Instant
import java.util.Date


@Composable
fun HomePage(
    viewModel: TodoListViewModel ,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        var title by rememberSaveable {
            mutableStateOf("")
        }

        var description by rememberSaveable {
            mutableStateOf("")
        }

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = { Text(text = "Title")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth()

        )
        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = { Text(text = "Description")},
            maxLines = 5,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(150.dp)
                .verticalScroll(rememberScrollState())
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                viewModel.addItems(title,description)
                title = ""
                description = ""
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text(text = "Add Item")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.TodoPage.name)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Todo Items")
        }
    }
}
@Composable
fun TodoListpage(
    viewModel: TodoListViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
//    val todoListItems = fakeList()

    val todoListItems by viewModel.todoItems.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {



        LazyColumn(
        ) {
            items(todoListItems.reversed()){items ->
                DisplayTodoList(item = items, onClick ={viewModel.deleteItem(items)})
            }
        }
    }


}

@Composable
fun DisplayTodoList(
    item: Todo,
    onClick: (Todo) -> Unit,
    modifier: Modifier = Modifier
) {
    var isTouched by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Blue)
            .padding(8.dp)
            .clickable {
                if (!isTouched) {
                    isTouched = true
                } else {
                    isTouched = false
                }
            },
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = SimpleDateFormat("d - MM - YYYY 'at' HH : mm aa").format(item.createdAt).toString(),
                fontSize = 10.sp,
                color = Color.White
            )

            IconButton(onClick = { onClick(item) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = Color.White
                )
            }
        }

        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            text = item.title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.White
        )

        if (isTouched) {
            Text(
                text = item.description,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(10.dp)
            )
        }

    }
}
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(Color.Blue)
//            .padding(8.dp),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//
//        Column(
//            modifier = Modifier
//                .weight(1f)
//                .padding(16.dp)
//                .clickable {
//                    if (!isTouched) {
//                        isTouched = true
//                    } else {
//                        isTouched = false
//                    }
//                }
//        ) {
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = item.createdAt.toString(),
//                    fontSize = 10.sp,
//                    color = Color.White
//                )
//
//                IconButton(onClick = {onClick(item)}) {
//                    Icon(
//                        imageVector = Icons.Default.Delete,
//                        contentDescription = "delete",
//                        tint = Color.White
//                    )
//                }
//            }
//
//            Text(
//                text = item.title,
//                style = TextStyle(
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold
//                ),
//                color = Color.White
//            )
//
//            if (isTouched){
//                Text(
//                    text = item.description,
//                    fontSize = 18.sp,
//                    color = Color.White,
//                    modifier = Modifier.padding(10.dp)
//                )
//            }
//
//        }
//
//
//    }
    



@Preview(showBackground = true)
@Composable
fun DisplayTodoListPreview(){
//    DisplayTodoList(item = Todo("Home work", "do the home work today itself", Date.from(Instant.now())))
//    TodoListpage()
}





















// a fake list for demo purpose
//fun fakeList(): List<Todo>{
//    return listOf(
//        Todo("Home Work","Do the home work today itself", Date.from(Instant.now())),
//        Todo("Home Work","Do the home work today itself", Date.from(Instant.now())),
//        Todo("Home Work","Do the home work today itself", Date.from(Instant.now())),
//        Todo("Home Work","Do the home work today itself", Date.from(Instant.now()))
//    )
//}