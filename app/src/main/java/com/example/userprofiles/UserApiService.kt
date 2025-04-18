package com.example.userprofiles

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoApiService {
    @GET("/todos")
    suspend fun getRandomTodo(): List<Todo>
}

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TodoApiService by lazy { retrofit.create(TodoApiService::class.java) }
}
