package com.example.data.retrofit.api

import com.example.data.data.LoginData
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DmLoginService {
    @POST("v1/member/login")
    fun loginResponse (
        @Body login: LoginData
    ): Call<JSONObject>
}