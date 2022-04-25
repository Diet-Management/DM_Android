package com.example.data.retrofit.api

import com.example.data.data.LoginData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DmLoginService {
    @POST("v1/member/login")
    fun loginResponse (
        @Body login: LoginData
    ): Call<ResponseBody>
}