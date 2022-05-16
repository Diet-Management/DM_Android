package com.example.data.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header

interface DmDeleteUserService {
    @DELETE
    fun deleteUser(
        @Header("Authorization") accessToken: String
    ): Call<ResponseBody>
}