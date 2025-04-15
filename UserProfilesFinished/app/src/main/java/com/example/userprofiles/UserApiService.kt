package com.example.userprofiles

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApiService {
    @GET("api/")
    suspend fun getRandomUser(): RandomUserResponse
}

data class RandomUserResponse(@SerializedName("results") val results: List<User>)

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}
