package com.example.fipa.network

import com.example.fipa.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Created by KING JINHO on 2020-02-25
 */
enum class FIPAAPIFIlter {}

private const val  BASE_URL = BuildConfig.APPLICATION_ID
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface FIPAApiService {
    @Hea
    @GET("api/user/authentication")
    suspend fun getAuthenticationStatus(@Header("Authorization") token : String, @Query("id") id: String): Response<Boolean>

}

object FIPAApi {
    val retrofitService : FIPAApiService by lazy {

        retrofit.create(FIPAApiService::class.java)
    }
}