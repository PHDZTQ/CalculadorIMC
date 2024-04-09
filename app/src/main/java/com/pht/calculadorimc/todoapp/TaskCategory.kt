package com.pht.calculadorimc.todoapp

sealed class TaskCategory {
    object Personal :TaskCategory()
    object Business :TaskCategory()
    object Other :TaskCategory()
}