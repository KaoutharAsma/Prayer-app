package com.example.prayerapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface IApi {
    @GET("timingsByCity")
    fun getPrayer(
        @Query("city") City:String,
        @Query("country") Country:String
    ): Deferred<PrayerResponse>
    companion object {
        operator fun invoke():IApi{
            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.aladhan.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IApi::class.java)

        }
    }
}
