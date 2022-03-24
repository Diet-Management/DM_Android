package com.example.data.retrofit

import com.example.data.data.JoinData
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DmJoinService {
    @POST("v1/member/join")
    fun joinResponse (
        @Body user: JoinData
    ): Call<JSONObject> // 정보를 보내야 하기 때문에
}