package com.example.fipa.network

import com.example.fipa.BuildConfig
import com.example.fipa.models.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * Created by KING JINHO on 2020-02-25
 */

private const val AUTHORIZATION = "Authorization"

private const val BASE_URL = "${BuildConfig.APPLICATION_ID}/api/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient().newBuilder().apply {
    connectTimeout(30, TimeUnit.SECONDS)
    readTimeout(30, TimeUnit.SECONDS)
    followRedirects(true)
    followSslRedirects(true)
    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
    addInterceptor { chain ->
        val newRequest = chain.request().newBuilder().addHeader(AUTHORIZATION, "sample").build()
        chain.proceed(newRequest)
    }
}.build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface FIPAApiService {

    @POST("user/{id}")
    suspend fun signup(@Path("id") id: String, @Body user: User): Response<User>

    @GET("user/authentication/{id}")
    suspend fun getAuthenticationStatus(@Path("id") id: String): Response<Boolean>

    @PATCH("user/token")
    suspend fun reissueToken(@Body user: User) : Response<String>

    @PATCH("user/password-reset")
    suspend fun reset()

    @DELETE("user/{id}")
    suspend fun unregister(@Path("id") id: String): Response<Boolean>

}

object FIPAApi {
    val retrofitService: FIPAApiService by lazy {

        retrofit.create(FIPAApiService::class.java)
    }
}