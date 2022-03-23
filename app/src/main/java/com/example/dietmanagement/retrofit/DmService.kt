package com.example.dietmanagement.retrofit

import com.example.dietmanagement.data.JoinData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DmService {
//    @FormUrlEncoded
    @POST("v1/member/join")
    fun joinResponse (
        @Body user: HashMap<String, String>
    ): Call<JoinData> // email, pw을 보내야 하기 때문에
}