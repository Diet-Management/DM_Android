package com.example.data.retrofit.builder

import com.example.data.base.Base
import com.example.data.retrofit.api.DmLoginService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DmLoginServiceBuilder {
    var dmLoginService: DmLoginService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmLoginService = retrofit.create(DmLoginService::class.java)
    }
}