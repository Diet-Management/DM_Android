package com.example.dietmanagement.retrofit

import com.example.dietmanagement.data.JoinData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DmService {
    @POST("v1/member/join")
    fun joinResponse (
        @Body user: JoinData
    ): Call<JSONObject> // 정보를 보내야 하기 때문에
}