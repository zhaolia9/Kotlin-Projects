package com.example.apihomework


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import okhttp3.ResponseBody
import retrofit2.Response


interface CoffeeApiService {
    @GET("random.json")
    suspend fun getCoffee(): Response<ResponseBody>
}

data class CoffeeResponse(
    val file: String
)

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://coffee.alexflipnote.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CoffeeApiService by lazy {
        retrofit.create(CoffeeApiService::class.java)
    }
}
