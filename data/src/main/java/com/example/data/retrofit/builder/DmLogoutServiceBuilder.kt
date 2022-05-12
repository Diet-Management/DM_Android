package com.example.data.retrofit.builder

import com.example.data.base.Base.BASE_URL
import com.example.data.retrofit.api.DmLogoutService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DmLogoutServiceBuilder {
    var dmLogoutService: DmLogoutService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmLogoutService = retrofit.create(DmLogoutService::class.java)
    }
}