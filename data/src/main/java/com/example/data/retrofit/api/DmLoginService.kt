package com.example.data.retrofit.api

import com.example.data.data.request.LoginData
import com.example.data.data.response.BaseResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DmLoginService {
    @POST("v1/member/login")
    fun loginResponse (
        @Body login: LoginData
    ): Call<BaseResponse>
}