package com.example.data.data

import com.google.gson.annotations.SerializedName

data class JoinSuccessData(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("msg")
    var msg: String,
    @SerializedName("status")
    var status: String
)