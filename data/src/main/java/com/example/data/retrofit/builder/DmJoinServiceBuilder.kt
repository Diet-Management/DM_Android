package com.example.data.retrofit.builder

import com.example.data.base.Base.BASE_URL
import com.example.data.retrofit.api.DmJoinService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// api 인터페이스가 호출 가능한 객체로 변환되는 클래스로 만들어줌
object DmJoinServiceBuilder {
    var dmApiService: DmJoinService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        dmApiService = retrofit.create(DmJoinService::class.java)
    }
}