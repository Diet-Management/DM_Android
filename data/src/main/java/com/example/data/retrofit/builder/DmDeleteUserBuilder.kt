package com.example.data.retrofit.builder

import com.example.data.base.Base.BASE_URL
import com.example.data.retrofit.api.DmDeleteUserService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DmDeleteUserBuilder {
    var dmDeleteUserService: DmDeleteUserService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        dmDeleteUserService = retrofit.create(DmDeleteUserService::class.java)
    }
}