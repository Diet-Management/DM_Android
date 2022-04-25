package com.example.data.retrofit.api

import com.example.data.data.JoinData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DmJoinService {
    @POST("v1/member/join")
    fun joinResponse (
        @Body user: JoinData
    ): Call<ResponseBody> // 정보를 보내야 하기 때문에
}