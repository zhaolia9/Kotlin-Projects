package com.example.apihomework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import okhttp3.ResponseBody

class CoffeeViewModel : ViewModel() {
    private val _coffeeImageUrl = MutableStateFlow<String?>(null)
    val coffeeImageUrl: StateFlow<String?> = _coffeeImageUrl

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchCoffee()
    }

    fun fetchCoffee() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null

            try {
                val response: Response<ResponseBody> = RetrofitInstance.api.getCoffee()
                if (response.isSuccessful) {
                    val json = response.body()?.string()
                    val imageUrl = JSONObject(json).getString("file")
                    _coffeeImageUrl.value = imageUrl
                } else {
                    _error.value = "HTTP Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Exception: ${e.message}"
            }

            _loading.value = false
        }
    }
}
