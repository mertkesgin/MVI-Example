package com.example.mertkesgin.mvi_example.data

import com.example.mertkesgin.mvi_example.utils.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val BASE_URL = "https://rickandmortyapi.com/api"

    fun <Api> buildApi(
            api: Class<Api>
    ): Api {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(api)
    }
}