package com.example.data.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("success") val success: String,
    @SerializedName("msg") val msg: String,
    @SerializedName("status") val status: String
)
