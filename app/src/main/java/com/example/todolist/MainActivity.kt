package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}
data class Tasks(val task: string, val isDone: boolean)

@Composable
fun TaskToDoList(){
    val taskToDoList = mutableStateListOf<Tasks>
    val taskToDo = mutableStateListOf<Tasks>

    //  For the User to add a new Task
    //  create a new Task obj, add it to the list
    fun addTask(){
        val newTask = Tasks(task = text, isDone = false)
        taskToDoList += newTask
    }

    fun toggleTask(){

    }
}

@Composable
fun TaskToDoScreen(){

}