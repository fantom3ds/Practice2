package com.example.practice2

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {

    companion object {
        val instance: APIClient by lazy { Holder.INSTANCE }
        //val instance = Holder.INSTANCE
        //val instance = APIClient()
    }

    private object Holder {
        val INSTANCE = APIClient()
    }

    //Переменная для запроса списка вечеринок
    val apiService: ApiService
    //Переменная для авторизации и регистрации

    //init можно создать общий
    //Блок, который будет выполнен при создании класса, инициализирует то что внутри
    init {
        apiService = createRetrofit(
            "https://api.openbrewerydb.org/",
            initOkHttpClient()
        ).create(ApiService::class.java)
    }

    private fun initOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()//для логирования запроса
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(interceptor)
            }
        }.build()
    }

    private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}