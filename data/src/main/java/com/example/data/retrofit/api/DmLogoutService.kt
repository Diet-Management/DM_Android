package com.example.data.retrofit.api

import com.example.data.data.response.BaseResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface DmLogoutService {
    @POST("/v1/member/logout")
    fun logoutResponse(
        @Header("Authorization") accessToken: String,
        @Header("RefreshToken") refreshToken: String
    ): Call<ResponseBody>
}