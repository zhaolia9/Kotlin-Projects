package com.example.userprofiles

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//Do this first

// defines Retrofit service UserApiService
interface UserApiService {
    // function makes a GET request to the https://randomuser.me/api/ endpoint
    @GET("api/")

    // suspend - must be called within a coroutine because it performs a network request
    // return type RandomUserResponse
    suspend fun getRandomUser(): RandomUserResponse
}

// models the JSON response from the API
// @SerializedName("results") annotation maps the results field in the
// JSON response to the results property in the data class
data class RandomUserResponse(@SerializedName("results") val results: List<User>)
// val results = list of User objects

// singleton obj initializes Retrofit, which is used to handle network requests
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create()) // converts JSON TO Kotlin using Gson
        .build() // build Retrofit instance

    // lazily initializes instance of UserApiService
    val api: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}

/*
When you call RetrofitInstance.api.getRandomUser(), it makes a network request to https://randomuser.me/api/.
The response JSON is automatically converted into a RandomUserResponse object.
The results field of RandomUserResponse contains a list of User objects.
* */