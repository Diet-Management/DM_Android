package com.example.data.retrofit.builder

import com.example.data.base.Base
import com.example.data.retrofit.api.DmLoginService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DmLoginServiceBuilder {
    var dmLoginService: DmLoginService

    init {
        val interceptor = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(Base.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmLoginService = retrofit.create(DmLoginService::class.java)
    }
}