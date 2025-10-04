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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme{
                TaskToDoList()
            }
        }
    }
}
data class Task(
    val task: String,
    val isDone: Boolean,
    val taskID: Int
)

@Composable
fun TaskToDoList() {
    //  tTD is short for taskToDo
    val tTDList = rememberSaveable { mutableStateListOf<Task>() } // ----- Tracking Tasks
    val tTDDone = rememberSaveable { mutableStateListOf<Task>() } // ----- Tracking Completed Tasks
    var nextID by rememberSaveable { mutableIntStateOf(0) } // -------------- Incrementer

    //  Self-explanatory function
    fun addTask(text: String) {
        tTDList.add(Task(task = text, isDone = false, taskID = nextID))
        nextID++
    }

    fun toggleTask(id: Int) {
        
    }

    fun removeTask(id: Int) {
        tTDList.removeIf { it.taskID == id }
        tTDDone.removeIf { it.taskID == id }
    }

    TaskToDoScreen(
        tasks = tTDList,
        completedTasks = tTDDone,
        onAdd = ::addTask,
        onToggle = ::toggleTask,
        onRemove = ::removeTask
    )
}

@Composable
fun TaskToDoScreen(
    tasks: List<Task>,
    completedTasks: List<Task>,
    onAdd: (String) -> Unit,
    onToggle: (Int) -> Unit,
    onRemove: (Int) -> Unit
) {
    // Build UI: Input TextField, Add Button, Active List, Completed List
}