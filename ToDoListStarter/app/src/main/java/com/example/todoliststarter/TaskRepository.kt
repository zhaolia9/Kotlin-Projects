package com.example.todoliststarter

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create DataStore instance
private val Context.dataStore by preferencesDataStore(name = "tasks")

class TaskRepository(private val context: Context) {
    /*****************/
    /* Do this first */
    /*****************/

    private val TASKS_KEY = stringSetPreferencesKey("tasks_key")

    val tasks: Flow<List<String>> = context.dataStore.data.map { preferences ->
        preferences[TASKS_KEY]?.toList() ?: emptyList()
    }

    suspend fun addTask(task: String) {
        context.dataStore.edit { preferences ->
            val currentTasks = preferences[TASKS_KEY] ?: emptySet()
            preferences[TASKS_KEY] = currentTasks + task
        }

    }

    suspend fun deleteTask(task: String) {

    }
}

