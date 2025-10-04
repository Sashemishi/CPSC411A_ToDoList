# CPSC411A_ToDoList
# Matthew Choi
* CPSC 411A

The purpose of this project is to create a simple To Do list for android using Kotlin and Jetpack Compose.
This project should show an understanding of the Kotlin language and the functions in Jetpack, and shows
the use of concepts of data classes, state, remember/rememberSaveable, and state hoisting.

First, we have our data class Task:
* Has a string, boolean, and an Int
* The string holds the task name/details
* The boolean allows us to toggle the task as complete or not
* the Int allows us to track each task with a unique number ID

We also have a viewmodel class TaskToDoVM:
* Here we have our Mutable State List of Task and an Int to track how many task were created to give Task items IDs
* The function addTask adds a Task Object to our mutable list, and increments our ID tracker
* The toggleTask function checks to see if the index is negative, if not, we toggle our boolean to be true
* The delTask function is self-explanatory
