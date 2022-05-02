package com.example.data.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DmMemberIdxService {
    @GET("/v1/member/{memberIdx}")
    fun getMemberIdx(
        @Path("memberIdx") memberIdx: Int
    ): Call<ResponseBody>
}