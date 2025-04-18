package com.example.userprofiles

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)
