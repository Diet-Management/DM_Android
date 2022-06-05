package com.example.data.retrofit.builder

import com.example.data.base.Base
import com.example.data.retrofit.api.DmProfileInfoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DmProfileInfoServiceBuilder {
    var dmProfileInfoService: DmProfileInfoService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmProfileInfoService = retrofit.create(DmProfileInfoService::class.java)
    }
}