package com.example.userprofiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// extends ViewModel (part of Android's Jetpack architecture)
// ensures data survives configuration changes
class UserViewModel : ViewModel() {
    //Do this second

    // _user stores fetched random user data
    private val _user = MutableStateFlow<User?>(null)
    // StateFlow<User?> allows UI components to observe changes
    // UI notified when a new user is fetched
    val user: StateFlow<User?> = _user

    // _loading tracks whether the API request is in progress
    private val _loading = MutableStateFlow(false)
    // use this to show loading spinner
    val loading: StateFlow<Boolean> = _loading

    // _error stores error messages if API request fails
    // displays error message when needed
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // automatically fetches a random user when UserViewModel is created
    init {
        fetchRandomUser()
    }

    // runs coroutine (viewModelScope.launch)
    fun fetchRandomUser() {
        viewModelScope.launch {
            // indicate fetching process has started
            _loading.value = true
            // clears any previous errors
            _error.value = null
            try {
                // calls RetrofitInstance.api.getRandomUser() to get user data
                val response = RetrofitInstance.api.getRandomUser()
                // if successful, _user updated with first user in the response
                _user.value = response.results.firstOrNull()
            } catch (e: Exception) {
                _error.value = "Failed to load user: ${e.message}"
            }
            // marks loading process complete
            _loading.value = false
        }
    }
}
