package com.example.data.retrofit.api

import com.example.data.data.response.BaseResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface DmProfileInfoService {
    @Multipart
    @PATCH("/v1/member/profile")
    fun getProfile(
        @Header("Authorization") accessToken: String,
        @Header("RefreshToken") refreshToken: String,
        @Part file: MultipartBody.Part
    ): Call<BaseResponse>
}