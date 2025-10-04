package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.example.todolist.ui.theme.ToDoListTheme



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
fun TaskToDoScreen(
    tasks: List<Task>,
    completedTasks: List<Task>,
    onAdd: (String) -> Unit,
    onToggle: (Int) -> Unit,
    onRemove: (Int) -> Unit
)
{

}