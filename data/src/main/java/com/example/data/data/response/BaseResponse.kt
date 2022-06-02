package com.example.data.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("success") var success: String,
    @SerializedName("msg") var msg: String,
    @SerializedName("status") var status: String
)
