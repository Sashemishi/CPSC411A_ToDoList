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
data class Task(
    val task: String,
    val isDone: Boolean,
    val taskID: Int
)

@Composable
fun TaskToDoList() {
    // Active tasks
    var taskToDoList by rememberSaveable { mutableStateOf(listOf<Task>()) }

    // Completed tasks
    val taskToDoDone = remember { mutableStateListOf<Task>() }

    var nextID by rememberSaveable { mutableStateOf(0) }

    // Add a new task
    fun addTask(text: String) {
        taskToDoList = taskToDoList + Task(task = text, isDone = false, taskID = nextID)
        nextID++
    }

    // Toggle task between done/undone
    fun toggleTask(id: Int) {
        taskToDoList = taskToDoList.map { task ->
            if (task.taskID == id) {
                val updatedTask = task.copy(isDone = !task.isDone)
                if (updatedTask.isDone) {
                    taskToDoDone.add(updatedTask) // add to completed
                } else {
                    taskToDoDone.removeIf { it.taskID == id } // remove from completed if undone
                }
                updatedTask
            } else task
        }
    }

    // Remove task completely
    fun removeTask(id: Int) {
        taskToDoList = taskToDoList.filter { it.taskID != id }
        taskToDoDone.removeIf { it.taskID == id }
    }

    // Here you would call a Composable to show the UI
    TaskToDoScreen(
        tasks = taskToDoList,
        completedTasks = taskToDoDone,
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