package com.example.kotlin.mydragonballapp.data.network

import com.example.kotlin.mydragonballapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {

    private val gsonFactory: GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

    // Retorna una instancia de DragonBallAPIService
    operator fun invoke(): DragonBallAPIService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(DragonBallAPIService::class.java)
    }
}
