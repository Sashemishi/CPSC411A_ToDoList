package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.todolist.ui.theme.ToDoListTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme{
                TaskTDScr()
            }
        }
    }
}
data class Task(
    val task: String,
    val isDone: Boolean,
    val taskID: Int
)

class TaskToDoVM : ViewModel(){
    //  tTD is short for taskToDo
    val tTDList = mutableStateListOf<Task>()
    private var nextID = 0

    fun addTask(label : String) {
        if (label.isNotBlank()) {
            tTDList.add(Task(task = label, isDone = false, taskID = nextID))
            nextID++
        }
    }

    fun toggleTask(id: Int) {
        val index = tTDList.indexOfFirst { it.taskID == id }
        if (index != -1) {
            val task = tTDList[index]
            tTDList[index] = task.copy(isDone = !task.isDone)
        }
    }

    fun delTask(id: Int){
        tTDList.removeIf{ it.taskID == id }
    }
}

@Composable
fun TaskTDScr(
    vm: TaskToDoVM = viewModel()
) {
    // Observe the list of tasks from the ViewModel
    val tasks = vm.tTDList

    val actTasks = tasks.filter { !it.isDone }
    val compTasks = tasks.filter { it.isDone }

    // 🔹 Local state for the input field
    var newTask by remember { mutableStateOf("") }

    // Example UI
    Column {
        // Input row
        Row {
            TextField(
                value = newTask,
                onValueChange = { newTask = it },
                placeholder = { Text("Enter a task...") }
            )
            Button(onClick = {
                vm.addTask(newTask)
                newTask = "" // clear field after adding
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Active Tasks")
        actTasks.forEach { task ->
            Row {
                Text(task.task)
                Button(onClick = { vm.toggleTask(task.taskID) }) {
                    Text("Done")
                }
                Button(onClick = { vm.delTask(task.taskID) }) {
                    Text("Delete")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Completed Tasks")
        compTasks.forEach { task ->
            Row {
                Text(task.task)
                Button(onClick = { vm.toggleTask(task.taskID) }) {
                    Text("Undo")
                }
                Button(onClick = { vm.delTask(task.taskID) }) {
                    Text("Delete")
                }
            }
        }
    }
}