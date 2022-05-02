package com.example.data.data.request

import com.google.gson.annotations.SerializedName

data class JoinData(
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("password")
    var password: String,
    @SerializedName("theme")
    var theme: String
)