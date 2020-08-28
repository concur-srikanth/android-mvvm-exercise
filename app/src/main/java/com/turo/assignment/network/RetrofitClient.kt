package com.turo.assignment.network

import com.turo.assignment.network.service.module.AddHeaderInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


open class RetrofitClient {

    companion object {

        private var retrofit: Retrofit? = null

        private val BASE_URL = "https://api.yelp.com/"

        /*
            Initializes Retrofit and provides instance
         */
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                val httpClient = OkHttpClient.Builder()
                    .connectTimeout(NETWORK_TIME_OUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(NETWORK_TIME_OUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(NETWORK_TIME_OUT.toLong(), TimeUnit.SECONDS)
                httpClient.addNetworkInterceptor(AddHeaderInterceptor())

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory( GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            }
            return retrofit
        }
    }

}
