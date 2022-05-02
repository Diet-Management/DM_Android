package com.example.data.retrofit.builder

import com.example.data.base.Base.BASE_URL
import com.example.data.retrofit.api.DmMemberIdxService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DmMemberIdxServiceBuilder {
    var dmMemberIdxService: DmMemberIdxService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmMemberIdxService = retrofit.create(DmMemberIdxService::class.java)
    }
}