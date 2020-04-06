package com.example.fipa.network

import com.example.fipa.BuildConfig
import com.example.fipa.models.User
import com.example.fipa.utils.ID
import com.example.fipa.utils.Settings
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.security.cert.CertificateFactory
import java.util.concurrent.TimeUnit

/**
 * Created by KING JINHO on 2020-02-25
 */

private const val AUTHORIZATION = "Authorization"

private const val BASE_URL = "https://172.17.208.97:5001/api/"
private const val BASE_URL_TEST = "https://172.17.208.97:5001/api/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
//val caInput:InputStream = BufferedInputStream(FileInputStream())

private val client = OkHttpClient().newBuilder().apply {
    connectTimeout(5, TimeUnit.SECONDS)
    cache(null)
    readTimeout(30, TimeUnit.SECONDS)
    writeTimeout(30, TimeUnit.SECONDS)
    followRedirects(true)
    followSslRedirects(true)
    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
    addInterceptor { chain ->
        val newRequest = chain.request().newBuilder().addHeader(AUTHORIZATION, Settings.TOKEN).build()
        chain.proceed(newRequest)
    }
}.build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(when(BuildConfig.DEBUG) {true -> BASE_URL_TEST else -> BASE_URL})
    .client(client)
    .build()


interface ApiService {

    @POST("user/{id}")
    suspend fun signUp(@Path("id") id: String, @Body user: User): Response<User>

    @POST("user/auth")
    suspend fun loginRequest(@Body user: User) : Response<User>

    @GET("user/auth/{ID}")
    suspend fun getAuthenticationStatus(@Path(ID) id: String): Response<Boolean>

    @PATCH("user/token")
    suspend fun reissueToken(@Body user: User) : Response<String>

    @PATCH("user/password-reset")
    suspend fun reset()

    @DELETE("user/{ID}")
    suspend fun unregister(@Path(ID) id: String): Response<Boolean>

    @GET("user/test/{ID}")
    suspend fun test(@Path(ID) id: String): Response<Boolean>

}

object FIPA_NETWORK {
    val REMOTE_SERVICE: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}