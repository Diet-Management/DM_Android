package com.example.data.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header

interface DmDeleteUserService {
    @DELETE("/v1/member")
    fun deleteUser(
        @Header("Authorization") accessToken: String,
        @Header("RefreshToken") refreshToken: String
    ): Call<ResponseBody>
}