package com.example.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// api 인터페이스가 호출 가능한 객체로 변환되는 클래스로 만들어줌
object RetrofitBuilder {
    var dmApiService: DmApiService
    val url: String = "http://10.53.68.201:8081/"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmApiService = retrofit.create(DmApiService::class.java)
    }
}